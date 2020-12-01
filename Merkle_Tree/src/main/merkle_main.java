package main;

import api.API_Block;
import api.API_SHA256;
import api.API_Merkle_Tree;
import impl.Block;
import impl.Merkle_Tree;
import impl.SHA256;

import javax.lang.model.type.ArrayType;
import java.sql.Struct;
import java.util.Scanner;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;


public class merkle_main {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        // Creates the Tx list
        ArrayList<String> Tx = new ArrayList<>();

        // Adds the content of the Tx List
        for (int i=0; i<8; i++){
            Tx.add(String.valueOf(i));
            System.out.printf("T"+ i + ": " + i + " | ");
        }
        System.out.println();

        // Creates the MkTree
        API_Merkle_Tree MkTree = new Merkle_Tree(Tx);
        MkTree.createTree();
        System.out.println("Root --> " + MkTree.Root());

        // Creates the block or Full Node with the Root and the Tx List
        API_Block block = new Block(MkTree.Root(), Tx);
        // Show the content on the screen
        System.out.println("Block Transactions: ");
        System.out.println(block.getTxList());
        System.out.println("Block root --> " + block.MkRoot());

        // Get the Transaction to search for.
        String Tn = "";
        Scanner entradaEscaner = new Scanner (System.in); //Creación de un objeto Scanner
        System.out.println("Ingrese la transaccion a buscar: ");
        Tn = entradaEscaner.nextLine (); //Invocamos un método sobre un objeto Scanner
        System.out.println("Ingrese la posision de la transaccion a buscar: ");
        int posTn = 0;
        posTn = Integer.parseInt(entradaEscaner.nextLine ()); //Invocamos un método sobre un objeto Scanner

        // Show if the Tx is valid or not.

        boolean verify = MkTree.verifyBlockTx(block,posTn,Tn);

        if (verify)
            System.out.println("The transaction is contained into the Block.");
        else
            System.out.println("The transaction is not contained into the Block or the number of transaction is wrong.");

    }
}
