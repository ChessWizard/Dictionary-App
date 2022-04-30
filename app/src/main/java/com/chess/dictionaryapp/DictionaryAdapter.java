package com.chess.dictionaryapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

// NOT: Adapter abstract class'ini en son eklemeliyiz.
public class DictionaryAdapter extends RecyclerView.Adapter<DictionaryAdapter.CardViewHolder> {

    // Properties
    private Context mContext;// Toast mesaj, veritabani islemleri gibi bircok islemde lazim olacagindan tanimliyoruz.
    private List<Kelimeler> dataList;// Adapter ile baglayacagimiz "veri" kumemiz

    // Constructor
    public DictionaryAdapter(Context mContext, List<Kelimeler> dataList) {
        this.mContext = mContext;
        this.dataList = dataList;
    }

    // RecyclerView uzerinde kullanilacak tasarimin inflate edildigi metot
    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // ViewGroup nesnesi android widget'larini tutar -> CardView, LinearLayout... gibi
        // CardView'un bulunacagi sayfanin Context'i alinir bu sayede (NOT: mContext de kullailabilirdi)
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dictionary_card_design,parent,false);

        return new CardViewHolder(view);// inflate edilen gorunum dondurulur
    }

    // inflate edilen gorunumlerle ilgili yapilacak islemlerin duzenlendigi metot
    // Her satirda neyin nasil goruntulenecegi baglanir
    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {

        // Veri tablomuzdaki veriler position ile satir satir alinir
        // ÖR: 3 veri var, 0-1-2. indisler icin sirasiyla veriler gelir
        Kelimeler kelime = dataList.get(position);

        // Veri gelirken tam bir satir halinde gelir
        // Bu satirdaki tum veriler icin duzenlememizi yapiyoruz
        holder.word_english.setText(kelime.getIngilizce());// ingilizce'si goruntulendi
        holder.word_turkish.setText(kelime.getTurkce());// turkce'si goruntulendi

        // Card'a tiklandiginda daha detayli bilgiye ulasmak istiyoruz
        // Bunun icin tiklama ozelligi tanimlamaliyiz
        holder.dictionary_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Kelimelerle ilgili detayli bilgi gostermek istersek
                // Bir class icindeyiz, aktivite degil!
                // Aktivite ozelligi olan Intent'i kullanmak icin de tanimladigimiz Context'i kullaniyoruz!
                Intent intent = new Intent(mContext,MainActivity2.class);
                intent.putExtra("word_english",kelime.getIngilizce());
                intent.putExtra("word_turkish",kelime.getTurkce());
                mContext.startActivity(intent);

                // Eger her tiklamada kelimeyi mesaj olarak gostermek istersek
                //Toast.makeText(mContext,"Seçilen kelime: "+kelime.getIngilizce(),Toast.LENGTH_SHORT).show();
            }
        });


    }

    // RecyclerView uzerinde "kac tane Card siralanacagini" tutan metot
    @Override
    public int getItemCount() {
        return dataList.size();
    }


    // Card uzerindeki nesneleri baglamak icin bu nesneleri tanimliyoruz
    // CardViewHolder bizim "nesneleri modellemek" icin yarattigimiz bir class'tir.
    public class CardViewHolder extends RecyclerView.ViewHolder{

        private TextView word_english;
        private TextView word_turkish;
        private CardView dictionary_card;


        // Constructor
        public CardViewHolder(@NonNull View itemView) {
            super(itemView);

            // Nesne ilk yaratildigi anda CardView uzerindeki objeler baglansin
            word_english = itemView.findViewById(R.id.word_english);
            word_turkish = itemView.findViewById(R.id.word_turkish);
            dictionary_card = itemView.findViewById(R.id.dictionary_card);
        }
    }


}
