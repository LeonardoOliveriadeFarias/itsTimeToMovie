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
    private Button compLink, compImage;
    TextView textTituloFilme;
    ImageView imagePoster;
    TextView textViewDescription;


    //coment
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_filme);

        textTituloFilme = findViewById(R.id.text_filme_Title_Detail);
        imagePoster = findViewById(R.id.imagePoster_Detail);
        textViewDescription = findViewById(R.id.description_filme);

        final Filme filme = (Filme) getIntent().getSerializableExtra(EXTRA_FILME);

        textTituloFilme.setText(filme.getTitle());
        Picasso.get()
                .load("https://image.tmdb.org/t/p/w342/" + filme.getPosterPath())
                .into(imagePoster);
        textViewDescription.setText(filme.getDescription());


        compLink = (Button) findViewById(R.id.Compartilhar_link);
        compLink.setOnClickListener(new View.OnClickListener(){public void onClick(View view){sharedLink(filme);}});

        compImage = (Button) findViewById(R.id.Compartilhar_imagem);
        compImage.setOnClickListener(new View.OnClickListener(){public void onClick(View view){sharedImage();}});


    }




    public void sharedLink(Filme v) {

        Intent sendIntent = new Intent(Intent.ACTION_SEND_MULTIPLE);
        sendIntent.setType("application/json");
        sendIntent.putExtra(Intent.EXTRA_SUBJECT, "Olhe este filme MARAVILHOSO!");
        sendIntent.putExtra(Intent.EXTRA_TEXT, v.getTitle());
        String text = "itstimetomovie.com.br/filme?id=" + v.getId();
        sendIntent.putExtra(Intent.EXTRA_TEXT, text);

            startActivity(Intent.createChooser(sendIntent,"enviar para:"));

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
            if(imagePoster.getDrawable() != null){

                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("image/jpeg");
                BitmapDrawable drawable = (BitmapDrawable) imagePoster.getDrawable();
                Bitmap bitmap = drawable.getBitmap();
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG,100, bytes);
                String path = MediaStore.Images.Media.insertImage(getContentResolver(),bitmap,
                        "Poster", null);
                Uri uri = Uri.parse(path);
                intent.putExtra(Intent.EXTRA_STREAM, uri);
                startActivity(Intent.createChooser(intent,"compartilhar imagem"));


            }else{
                Toast.makeText(getBaseContext(),"Não foi possivel compartilhar imagem", Toast.LENGTH_LONG).show();
            }
    }

}
