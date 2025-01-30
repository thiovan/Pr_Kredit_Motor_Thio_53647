<?php
error_reporting(E_ALL & ~E_DEPRECATED);
require_once('tcpdf/tcpdf.php');

$server = "localhost";
$username = "root";
$password = "";
$database = "db_android_1";
$koneksi = mysqli_connect($server, $username, $password, $database);

@$invoice = $_GET['invoice'];
$query_tampil_petugas = mysqli_query(
  $koneksi,
  "SELECT kredit.invoice, kredit.tanggal, kredit.idkreditor,kreditor.nama, kreditor.alamat, kredit.kdmotor, motor.nama as nmotor, kredit.hrgtunai, kredit.dp, kredit.hrgkredit, kredit.bunga, kredit.lama, kredit.totalkredit, kredit.angsuran
  FROM motor INNER JOIN (kreditor INNER JOIN kredit ON kreditor.idkreditor = kredit.idkreditor) ON motor.kdmotor = kredit.kdmotor
  WHERE kredit.invoice = '$invoice' ORDER BY kredit.invoice DESC"
) or die(mysqli_error($koneksi));
$data_array = array();
while ($data = mysqli_fetch_assoc($query_tampil_petugas)) {
  $data_array[] = $data;
}

$pdf = new TCPDF(PDF_PAGE_ORIENTATION, PDF_UNIT, PDF_PAGE_FORMAT, true, 'UTF-8', false);

$pdf->SetCreator(PDF_CREATOR);
$pdf->SetAuthor('');
$pdf->SetTitle('Data Pengajuan Kredit');

$pdf->setHeaderFont(Array(PDF_FONT_NAME_MAIN, '', PDF_FONT_SIZE_MAIN));
$pdf->setFooterFont(Array(PDF_FONT_NAME_DATA, '', PDF_FONT_SIZE_DATA));

$pdf->SetDefaultMonospacedFont(PDF_FONT_MONOSPACED);

$pdf->SetMargins(PDF_MARGIN_LEFT, PDF_MARGIN_TOP, PDF_MARGIN_RIGHT);
$pdf->SetHeaderMargin(PDF_MARGIN_HEADER);
$pdf->SetFooterMargin(PDF_MARGIN_FOOTER);

$pdf->SetAutoPageBreak(TRUE, PDF_MARGIN_BOTTOM);

$pdf->setImageScale(PDF_IMAGE_SCALE_RATIO);

$pdf->SetFont('helvetica', '', 12);

$pdf->AddPage();

$pdf->Cell(0, 10, 'DATA PENGAJUAN KREDIT', 1, 1, 'C');

$pdf->Ln(10);

$pdf->SetFillColor(255, 255, 255);
$pdf->SetTextColor(0);
$pdf->SetDrawColor(0, 0, 0);

$pdf->Ln(10);

foreach ($data_array as $data) {
  $pdf->Cell(0, 10, 'Invoice        : ' . $data['invoice'], 1, 1, 'L', 0);
  $pdf->Cell(0, 10, 'Tanggal        : ' . $data['tanggal'], 1, 1, 'L', 0);
  $pdf->Cell(0, 10, 'Nama Kreditor   : ' . $data['nama'], 1, 1, 'L', 0);
  $pdf->Cell(0, 10, 'Alamat Kreditor : ' . $data['alamat'], 1, 1, 'L', 0);
  $pdf->Cell(0, 10, 'Kode Motor      : ' . $data['kdmotor'], 1, 1, 'L', 0);
  $pdf->Cell(0, 10, 'Nama Motor      : ' . $data['nmotor'], 1, 1, 'L', 0);
  $pdf->Cell(0, 10, 'Harga Tunai     : ' . $data['hrgtunai'], 1, 1, 'L', 0);
  $pdf->Cell(0, 10, 'DP              : ' . $data['dp'], 1, 1, 'L', 0);
  $pdf->Cell(0, 10, 'Harga Kredit    : ' . $data['hrgkredit'], 1, 1, 'L', 0);
  $pdf->Cell(0, 10, 'Bunga           : ' . $data['bunga'] . '%', 1, 1, 'L', 0);
  $pdf->Cell(0, 10, 'Lama            : ' . $data['lama'] . ' bulan', 1, 1, 'L', 0);
  $pdf->Cell(0, 10, 'Total Kredit    : ' . $data['totalkredit'], 1, 1, 'L', 0);
  $pdf->Cell(0, 10, 'Angsuran        : ' . $data['angsuran'], 1, 1, 'L', 0);
}

$pdf->Ln(20);
$pdf->Cell(0, 10, 'Tanda Tangan Sales:', 0, 1, 'L', 0);
$pdf->Ln(20);
$pdf->Cell(0, 10, '_________________________', 0, 1, 'L', 0);

$pdf->Output('laporan_kredit_' . $invoice . '.pdf', 'I');

