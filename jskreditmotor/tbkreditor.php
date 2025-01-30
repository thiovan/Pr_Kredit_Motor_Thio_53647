<?php

$server = "localhost";
$username = "root";
$password = "";
$database = "db_android_1";

$koneksi = mysqli_connect($server, $username, $password, $database);

@$operasi = $_GET['operasi'];

switch ($operasi) {

  case "view":

    /* Source code untuk Menampilkan kreditor */
    $query_tampil_kreditor = mysqli_query($koneksi, "SELECT * FROM kreditor") or die(mysqli_error($koneksi));
    $data_array = array();
    while ($data = mysqli_fetch_assoc($query_tampil_kreditor)) {
      $data_array[] = $data;
    }
    echo json_encode($data_array);
    break;

  case "select_by_idnama":

    /* Source code untuk Menampilkan kreditor */
    $query_by_idnama = mysqli_query($koneksi, "SELECT idkreditor, nama FROM kreditor ORDER BY idkreditor DESC") or die(mysqli_error($koneksi));
    $data_array = array();
    while ($data = mysqli_fetch_assoc($query_by_idnama)) {
      $data_array[] = $data;
    }
    echo json_encode($data_array);
    break;

  case "select_by_idkreditor":

    @$idkreditor = $_GET['idkreditor'];
    /* Source code untuk Menampilkan kreditor */
    $query_by_idkreditor = mysqli_query($koneksi, "SELECT * FROM kreditor WHERE idkreditor='$idkreditor'") or die(mysqli_error($koneksi));
    $data_array = array();
    while ($data = mysqli_fetch_assoc($query_by_idkreditor)) {
      $data_array[] = $data;
    }
    echo json_encode($data_array);
    break;

  case "insert":
    @$nama = str_replace("%", " ", $_GET['nama']);
    @$pekerjaan = str_replace("%", " ", $_GET['pekerjaan']);
    @$telp = str_replace("%", " ", $_GET['telp']);
    @$alamat = str_replace("%", " ", $_GET['alamat']);

    $query_insert_data = mysqli_query($koneksi, "INSERT INTO kreditor (nama, pekerjaan, telp, alamat) VALUES('$nama', '$pekerjaan', '$telp', '$alamat')");
    if ($query_insert_data) {
      echo "Data Berhasil Disimpan";
    } else {
      echo "Error Insert kreditor " . mysqli_error($koneksi);
    }
    break;

  case "get_kreditor_by_idkreditor":
    @$idkreditor = $_GET['idkreditor'];

    $query_tampil_kreditor = mysqli_query($koneksi, "SELECT * FROM kreditor WHERE idkreditor='$idkreditor'") or die(mysqli_error($koneksi));
    $data_array = array();
    $data_array = mysqli_fetch_assoc($query_tampil_kreditor);
    echo "[" . json_encode($data_array) . "]";
    break;

  case "update":
    @$idkreditor = $_GET['idkreditor'];
    @$nama = str_replace("%", " ", $_GET['nama']);
    @$pekerjaan = str_replace("%", " ", $_GET['pekerjaan']);
    @$telp = str_replace("%", " ", $_GET['telp']);
    @$alamat = str_replace("%", " ", $_GET['alamat']);

    $query_update_kreditor = mysqli_query($koneksi, "UPDATE kreditor SET nama='$nama', pekerjaan='$pekerjaan', telp='$telp', alamat='$alamat' WHERE idkreditor='$idkreditor'");
    if ($query_update_kreditor) {
      echo "Data Berhasil Diupdate";
    } else {
      echo "Error Update kreditor " . mysqli_error($koneksi);
    }
    break;

  case "delete":
    @$idkreditor = $_GET['idkreditor'];

    $query_delete_kreditor = mysqli_query($koneksi, "DELETE FROM kreditor WHERE idkreditor='$idkreditor'");
    if ($query_delete_kreditor) {
      echo "Data Berhasil Dihapus";
    } else {
      echo "Error Delete kreditor " . mysqli_error($koneksi);
    }
    break;

  default:
    break;
}
