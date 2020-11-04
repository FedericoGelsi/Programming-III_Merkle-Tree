package impl;

import api.API_Merkle_Tree;
import api.API_SHA256;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class Merkle_Tree implements API_Merkle_Tree {

    private ArrayList<String> Tx;

    private ArrayList<String> MkTree;

    private API_SHA256 hash = new SHA256();

    public Merkle_Tree(){

    }

    public Merkle_Tree(ArrayList<String> Tx){
        this.Tx = Tx;
    }
    @Override
    public void newTree(){
        this.Tx = new ArrayList<>();
        this.MkTree = new ArrayList<>();
    }

    @Override
    public String Root() {
        return this.MkTree.get(0);
    }

    @Override
    public void addTransaction(String Tr) throws NoSuchAlgorithmException {
        this.Tx.add(Tr);
    }

    @Override
    public void createTree() throws NoSuchAlgorithmException {
        this.MkTree = recreate_MkTree(this.Tx);
    }


    private ArrayList<String> recreate_MkTree (ArrayList<String> Tx) throws NoSuchAlgorithmException {
        if(Tx.size() == 1){
            return Tx;
        }
        ArrayList<String> parentTx = new ArrayList<>();

        for (int i = 0; i+1 < Tx.size(); i += 2) {
            System.out.println("Branch " + (i + 1) + " is " + this.hash.getSHA_Str(Tx.get(i)));
            System.out.println("Branch " + (i + 2) + " is " + this.hash.getSHA_Str(Tx.get(i + 1)));
            String hashedStr = this.hash.getSHA_Str(Tx.get(i).concat(Tx.get(i + 1)));
            System.out.println("Their hash is " + hashedStr);
            parentTx.add(hashedStr);
        }
        if (Tx.size() % 2 == 1){
            System.out.println("Branch "+ Tx.size() + " is ");
            String lastHash = Tx.get(Tx.size()-1);
            String hashedStr = this.hash.getSHA_Str(lastHash.concat(lastHash));
            parentTx.add(hashedStr);
        }
        return recreate_MkTree(parentTx);
    }
}
