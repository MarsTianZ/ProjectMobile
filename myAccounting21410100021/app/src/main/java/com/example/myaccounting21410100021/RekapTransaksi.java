package com.example.myaccounting21410100021;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class RekapTransaksi extends AppCompatActivity{
    ArrayList<Transaksi> listTransaksiClone = new ArrayList<>();
    Integer totalKredit = 0, totalDebit = 0, totalSeluruh = 0;
    private Button btnLaporanRekap;
    private TextView rekapPemasukan, rekapPengeluaran, rekapSaldo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rekap_transaksi);

        Intent intent = getIntent();
        btnLaporanRekap = (Button) findViewById(R.id.btnLaporanRekap);
        rekapPemasukan = (TextView) findViewById(R.id.rekapPemasukan);
        rekapPengeluaran = (TextView) findViewById(R.id.rekapPengeluaran);
        rekapSaldo = (TextView) findViewById(R.id.rekapSaldo);

        ArrayList<Transaksi> listTransaksi = (ArrayList<Transaksi>) intent.getSerializableExtra("list");
        listTransaksiClone = (ArrayList<Transaksi>) listTransaksi.clone();
        pindahKeMain();

        hitungTotalTrx();

        setRekap();
    }

    private void setRekap() {
        rekapPemasukan.setText("Rp. "+totalDebit.toString());
        rekapPengeluaran.setText("Rp. "+totalKredit.toString());
        rekapSaldo.setText("Rp. "+totalSeluruh.toString());
    }

    private void hitungTotalTrx() {
        for (int i = 0; i < listTransaksiClone.size(); i++) {
            totalKredit += listTransaksiClone.get(i).getKredit();
            Log.d("TAG", "hitungTotalTrx: " + totalKredit);
            totalDebit += listTransaksiClone.get(i).getDebit();
            Log.d("TAG", "hitungTotalTrx: " + totalDebit);
            totalSeluruh = totalDebit - totalKredit;
            Log.d("TAG", "hitungTotalTrx: " + totalKredit);
        }
    }

    private void pindahKeMain() {
        btnLaporanRekap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
