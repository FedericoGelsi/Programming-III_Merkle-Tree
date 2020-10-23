package impl;

import api.API_Block;

import java.util.ArrayList;

public class Block implements API_Block {

    private String root_hash;
    private ArrayList<String> Tx;


    public Block(String root_hash, ArrayList Tx){
        this.root_hash = root_hash;
        this.Tx = Tx;
    }

    @Override
    public String MkRoot() {
        return this.root_hash;
    }

    @Override
    public ArrayList<String> getTxList() {
        return this.Tx;
    }
}
