package com.thio.pr_kredit_motor_thio_53647;

public class Kreditor extends Koneksi {
    private long id;
    Server server = new Server();

    String SERVER = server.urlDatabase1();
    String URL = "http://" + SERVER + "/jskreditmotor/tbkreditor.php";
    String url = "";
    String response = "";

    public String tampilKreditor() {
        try {
            url = URL + "?operasi=view";
            System.out.println("URL Tampil Kreditor: " + url);
            response = call(url);
        } catch (Exception e) {
        }
        return response;
    }

    public String tampilKreditorbyIdNama() {
        try {
            url = URL + "?operasi=select_by_idnama";
            System.out.println("URL Tampil Kreditor: " + url);
            response = call(url);
        } catch (Exception e) {
        }
        return response;
    }

    public String insertKreditor(String nama, String pekerjaan, String telp, String alamat) {
        nama = nama.replace(" ", "%20");
        alamat = alamat.replace(" ", "%20");

        try {
            url = URL + "?operasi=insert&nama=" + nama + "&pekerjaan=" + pekerjaan + "&telp=" + telp + "&alamat=" + alamat;
            System.out.println("URL Insert Kreditor : " + url);
            response = call(url);
        } catch (Exception e) {
        }
        return response;
    }

    public String getKreditorByIdKreditor(int idkreditor) {
        try {
            url = URL + "?operasi=get_kreditor_by_idkreditor&idkreditor=" + idkreditor;
            System.out.println("URL Get Kreditor: " + url);
            response = call(url);
        } catch (Exception e) {
        }
        return response;
    }

    public String updateKreditor(String idkreditor, String nama, String pekerjaan, String telp, String alamat) {
        nama = nama.replace(" ", "%20");
        alamat = alamat.replace(" ", "%20");

        try {
            url = URL + "?operasi=update&idkreditor=" + idkreditor + "&nama=" + nama + "&pekerjaan=" + pekerjaan + "&telp=" + telp + "&alamat=" + alamat;
            System.out.println("URL Update Kreditor : " + url);
            response = call(url);
        } catch (Exception e) {
        }
        return response;
    }

    public String deleteKreditor(int idkreditor) {
        try {
            url = URL + "?operasi=delete&idkreditor=" + idkreditor;
            System.out.println("URL Hapus Kreditor : " + url);
            response = call(url);
        } catch (Exception e) {
        }
        return response;
    }

    public long getId() {
        return id;
    }
}
