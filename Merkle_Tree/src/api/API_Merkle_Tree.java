package api;

import java.security.NoSuchAlgorithmException;

public interface API_Merkle_Tree {
    void newTree();
    String Root();
    void addTransaction(String Tr) throws NoSuchAlgorithmException;
    void createTree() throws NoSuchAlgorithmException;
}
