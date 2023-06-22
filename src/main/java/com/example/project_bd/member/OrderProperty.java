package com.example.project_bd.member;

public class OrderProperty {
    private String kolom_jenisJasa;
    private int kolom_jasaID;
    private int kolom_kuantitas;
    private int kolom_harga;
    private int kolom_subtotal;

    public OrderProperty(String kolom_jenisJasa, int kolom_jasaID, int kolom_kuantitas, int kolom_harga, int kolom_subtotal) {
        this.kolom_jenisJasa = kolom_jenisJasa;
        this.kolom_jasaID = kolom_jasaID;
        this.kolom_kuantitas = kolom_kuantitas;
        this.kolom_harga = kolom_harga;
        this.kolom_subtotal = kolom_subtotal;
    }

    public String getKolom_jenisJasa() {
        return kolom_jenisJasa;
    }

    public int getKolom_jasaID(){
        return kolom_jasaID;
    }

    public int getKolom_kuantitas() {
        return kolom_kuantitas;
    }

    public int getKolom_harga() {
        return kolom_harga;
    }

    public int getKolom_subtotal() {
        return kolom_subtotal;
    }
}
