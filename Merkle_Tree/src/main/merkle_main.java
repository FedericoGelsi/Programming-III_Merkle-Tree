package main;

import api.API_SHA256;
import api.API_Merkle_Tree;
import impl.Merkle_Tree;
import impl.SHA256;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;


public class merkle_main {
    public static void main(String[] args) throws NoSuchAlgorithmException {

        // Merkle Tree adding one by one the transactions
        API_Merkle_Tree MkTree = new Merkle_Tree();
        MkTree.newTree();
        MkTree.addTransaction("AA");
        MkTree.addTransaction("BB");
        MkTree.addTransaction("CC");
        MkTree.addTransaction("DD");
        MkTree.addTransaction("11");
        MkTree.addTransaction("22");
        MkTree.addTransaction("33");
        MkTree.addTransaction("44");
        MkTree.createTree();
        System.out.println("Root --> " + MkTree.Root());

        // Merkle Tree adding the hole TxList on the creation of the tree
        ArrayList<String> Tx = new ArrayList<String>();
        Tx.add("2f8ea0f6e95594bb189fa5d2a1e26a0cd195344bb9aca0fcdb35330b3b258e40");
        Tx.add("c754176ca665995cd864299650c7e2900c772b43c4ef3268240295b2b87e3d58");
        Tx.add("b3282a2f2a28757b3a18ab833de16a9c54518c0b0cf493e3f0a7cf09386f326a");
        Tx.add("15fc36b3e80b9d7f87f7dc90cd7a2845c5d8501c30f03379fcf14154f1680380");
        API_Merkle_Tree MkTree2 = new Merkle_Tree(Tx);
        MkTree2.createTree();
        System.out.println("Root --> " + MkTree2.Root());
    }
}
