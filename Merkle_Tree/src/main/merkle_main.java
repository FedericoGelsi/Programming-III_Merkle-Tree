package main;

import api.API_Block;
import api.API_SHA256;
import api.API_Merkle_Tree;
import impl.Block;
import impl.Merkle_Tree;
import impl.SHA256;
import java.util.Scanner;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;


public class merkle_main {
    public static void main(String[] args) throws NoSuchAlgorithmException {

        // Merkle Tree adding the hole TxList on the creation of the tree
        ArrayList<String> Tx = new ArrayList<>();
        for (int i=0; i<9; i++){
            Tx.add(String.valueOf(i));
            System.out.println("T"+ (i+1) + ": " + i);
        }
        API_Merkle_Tree MkTree = new Merkle_Tree(Tx);
        MkTree.createTree();
        System.out.println("Root --> " + MkTree.Root());

        API_Block block = new Block(MkTree.Root(), Tx);

        System.out.println(block.getTxList());
        System.out.println(block.MkRoot());

        String Tn = "";
        Scanner entradaEscaner = new Scanner (System.in); //Creación de un objeto Scanner
        System.out.println("Ingrese la transaccion a buscar: ");
        Tn = entradaEscaner.nextLine (); //Invocamos un método sobre un objeto Scanner
        System.out.println("Ingrese la posision de la transaccion a buscar: ");
        int posTn = 0;
        posTn = Integer.parseInt(entradaEscaner.nextLine ()); //Invocamos un método sobre un objeto Scanner
        System.out.println(verifyBlockTx(block,posTn,Tn));

    }

    public static boolean verifyBlockTx(API_Block B, int posTn, String Tn) throws NoSuchAlgorithmException {
        ArrayList<String> Tx = B.getTxList();
        if (Tx.size() >= posTn){
            Tx.set(posTn,Tn);
            System.out.println(Tx);
            API_Merkle_Tree MkTree = new Merkle_Tree(Tx);
            MkTree.createTree();
            System.out.println("Root --> " + MkTree.Root());
            if (MkTree.Root().equals(B.MkRoot())){
                return true;
            }else{
                return false;
            }
        }else {
            return false;
        }
    }
}
