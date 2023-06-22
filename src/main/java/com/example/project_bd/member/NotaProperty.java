package com.example.project_bd.member;

import javafx.beans.property.*;

public class NotaProperty {
    // CONSTRUCTOR
    public NotaProperty() {
    }

    public NotaProperty(int notaId, String tanggalPemesanan, String siapDiambil, String selesai) {
        setNotaId(notaId);
        setTanggalPemesanan(tanggalPemesanan);
        setSiapDiambil(siapDiambil);
        setSelesai(selesai);
    }

    // ATTRIBUTE dalam table
    private IntegerProperty notaId;
    public void setNotaId(int value) { notaIdProperty().set(value); }
    public int getNotaId() { return notaIdProperty().get(); }
    public IntegerProperty notaIdProperty() {
        if (notaId == null) notaId = new SimpleIntegerProperty(this, "notaId");
        return notaId;
    }

    private StringProperty tanggalPemesanan;
    public void setTanggalPemesanan(String value) { tanggalPemesananProperty().set(value); }
    public String getTanggalPemesanan() { return tanggalPemesananProperty().get(); }
    public StringProperty tanggalPemesananProperty() {
        if (tanggalPemesanan == null) tanggalPemesanan = new SimpleStringProperty(this, "tanggalPemesanan");
        return tanggalPemesanan;
    }

    private StringProperty siapDiambil;
    public void setSiapDiambil(String value) { siapDiambilProperty().set(value); }
    public String getSiapDiambil() { return siapDiambilProperty().get(); }
    public StringProperty siapDiambilProperty() {
        if (siapDiambil == null) siapDiambil = new SimpleStringProperty(this, "siapDiambil");
        return siapDiambil;
    }

    private StringProperty selesai;
    public void setSelesai(String value) { selesaiProperty().set(value); }
    public String getSelesai() { return selesaiProperty().get(); }
    public StringProperty selesaiProperty() {
        if (selesai == null) selesai = new SimpleStringProperty(this, "selesai");
        return selesai;
    }
}
