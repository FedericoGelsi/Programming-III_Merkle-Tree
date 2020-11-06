package impl;

import api.API_Block;
import api.API_Merkle_Tree;
import api.API_SHA256;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class Merkle_Tree implements API_Merkle_Tree {

    private ArrayList<String> Tx;

    private ArrayList<String> MkTree;

    private ArrayList<String> Mkpath;

    private API_SHA256 hash = new SHA256();





    public Merkle_Tree(ArrayList<String> Tx){
        this.Tx = Tx;
    }


    @Override
    public String Root() {
        return this.MkTree.get(0);
    }


    @Override
    public void createTree() throws NoSuchAlgorithmException {
        this.MkTree = recreate_MkTree(this.Tx);
        System.out.println(this.MkTree);
    }


    private ArrayList<String> recreate_MkTree (ArrayList<String> Tx) throws NoSuchAlgorithmException {
        ArrayList<String> parentTx = new ArrayList<>();
        String hashTx = "";

        // Total of nodes
        int stop = 2*Tx.size()-1;

        // Adds all the hashed transactions
        for (int i=0; i<Tx.size(); i++){
            hashTx = this.hash.getSHA_Str(Tx.get(i));
            parentTx.add(hashTx);
        }

        if ( Tx.size()%2 != 0){
            parentTx.add(parentTx.get(parentTx.size()-1));
        }


        int index = parentTx.size()-1;
        int check = index;
        while (parentTx.size() < stop){
            hashTx = this.hash.getSHA_Str(parentTx.get(index-1) + parentTx.get(index));
            parentTx.add(0,hashTx);

            if (parentTx.size()%2 != 0 && check==0) {
                parentTx.add(parentTx.size() - Tx.size(), hashTx);
                check = parentTx.size() - 1;
                index++;
            }
            check--;

            index--;
        }
        return parentTx;
    }

    @Override
    public ArrayList<String> getMkTree(){
        return this.MkTree;
    }

    @Override
    public boolean verifyBlockTx(API_Block B, int posTn, String Tn) throws NoSuchAlgorithmException {
        this.Mkpath = new ArrayList<>();

        // Calculate the position of the transaction into the Merkle Tree
        int pos = posTn + this.MkTree.size() - B.getTxList().size();

        this.Mkpath = getPath(pos);

        String root = getRootfromHashes(Tn);
        System.out.println("Path root: " + root);

        if (this.MkTree.get(0).equals(root)){
            return true;
        }else{
            return false;
        }
    }

    // Returns the path to recreate the root of the Merkle Tree
    private ArrayList<String> getPath(int pos){
        if (pos > 0){
            if (pos%2 == 0){
                this.Mkpath.add(this.MkTree.get(pos-1));
            }else{
                this.Mkpath.add(this.MkTree.get(pos+1));
            }
            return getPath((pos-1)/2);
        }
        return this.Mkpath;
    }


    private String getRootfromHashes(String Tn) throws NoSuchAlgorithmException {
        String hash = "";
        String root = this.hash.getSHA_Str(Tn);
        for (int i = 0; i < this.Mkpath.size(); i++){
            hash = this.Mkpath.get(i);
            int pos = this.MkTree.indexOf(hash);
            if (pos != -1) {
                if (pos % 2 == 0) {
                    root = this.hash.getSHA_Str(root+ hash);
                } else {
                    root = this.hash.getSHA_Str(hash + root);
                }
            }
        }
        return root;
    }
}
