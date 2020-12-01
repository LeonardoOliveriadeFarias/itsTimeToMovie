package com.example.itsTimeToMovie.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.itsTimeToMovie.R;
import com.example.itsTimeToMovie.data.Model.Filme;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;

public class DetalhesFilmeActivity extends AppCompatActivity {

    public static final String EXTRA_FILME =  "EXTRA_FILME";
    private Button comp;


    //coment
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_filme);

        TextView textTituloFilme = findViewById(R.id.text_filme_Title_Detail);
        ImageView imagePoster = findViewById(R.id.imagePoster_Detail);
        TextView textViewDescription = findViewById(R.id.description_filme);

        final Filme filme = (Filme) getIntent().getSerializableExtra(EXTRA_FILME);

        textTituloFilme.setText(filme.getTitle());
        Picasso.get()
                .load("https://image.tmdb.org/t/p/w342/" + filme.getPosterPath())
                .into(imagePoster);
        textViewDescription.setText(filme.getDescription());


        comp = (Button) findViewById(R.id.Compartilhar_button);
        comp.setOnClickListener(new View.OnClickListener(){public void onClick(View view){compLink(filme, imagePoster);}});
    }




    public void compLink(Filme v, ImageView imagePoster) {

        Intent sendIntent = new Intent(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_SUBJECT, "Olhe este filme MARAVILHOSO!");
        sendIntent.putExtra(Intent.EXTRA_TEXT, v.getTitle());
        sendIntent.setType("application/json");
        String text = "itstimetomovie.com.br/filme?id=" + v.getId();
        sendIntent.putExtra(Intent.EXTRA_TEXT, text);

        Drawable drawable = imagePoster.getDrawable();
        Bitmap b = ((BitmapDrawable)drawable).getBitmap();
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("image/jpeg");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        b.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(getContentResolver(), b, "Titulo da Imagem", null);
        Uri imageUri =  Uri.parse(path);
        share.putExtra(Intent.EXTRA_STREAM, imageUri);

        if(sendIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(Intent.createChooser(sendIntent,"enviar para:"));
        } else {
            Toast.makeText(getApplicationContext(), "Erro", Toast.LENGTH_SHORT).show();
        }
    }



    public void enviarWhatsApp(View v) {
        PackageManager pm=getPackageManager();
        try {

            Intent waIntent = new Intent(Intent.ACTION_SEND);
            waIntent.setType("text/plain");
            String text = "itstimetomovie.com.br/filme?id=" + v.getId();

            PackageInfo info=pm.getPackageInfo("com.whatsapp", PackageManager.GET_META_DATA);
            waIntent.setPackage("com.whatsapp");

            waIntent.putExtra(Intent.EXTRA_TEXT, text);
            startActivity(waIntent);

        } catch (PackageManager.NameNotFoundException e) {
            Toast.makeText(this, "WhatsApp não instalado", Toast.LENGTH_SHORT).show();
        }
    }

    private void sharedImage(){

    }

}
