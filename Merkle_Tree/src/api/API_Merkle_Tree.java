package api;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public interface API_Merkle_Tree {
    String Root();

    void createTree() throws NoSuchAlgorithmException;

    ArrayList<String> getMkTree();

    boolean verifyBlockTx(API_Block B, int posTn, String Tn) throws NoSuchAlgorithmException;
}
