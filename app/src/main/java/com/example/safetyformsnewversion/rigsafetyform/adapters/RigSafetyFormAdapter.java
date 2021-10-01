package com.example.safetyformsnewversion.rigsafetyform.adapters;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.safetyformsnewversion.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class RigSafetyFormAdapter extends  RecyclerView.Adapter<RigSafetyFormAdapter.CostomViewHolder>
{
    Button button_dialog_ok;
    EditText editText_note;
    RadioGroup radioGroup;
    TextView textView_title;
    String _title;



    private Context mcontext;
    private List<String> myList;




    public RigSafetyFormAdapter(Context mcontext, List<String> myList)
    {
        this.mcontext = mcontext;
        this.myList = myList;
    }


    @NonNull
    @Override
    public CostomViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        LayoutInflater inflater = LayoutInflater.from(mcontext);
        View view = inflater.inflate(R.layout.title_list_view, null);
        CostomViewHolder holder = new CostomViewHolder(view);

        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull final CostomViewHolder holder, @SuppressLint("RecyclerView") final int position)
    {

        Log.i("Drop","onBindViewHolder");

        holder.textView_title.setText(myList.get(position));

        holder.imageView.setImageResource(R.drawable.ic_baseline_error_outline_24);

        holder.linear_row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                AlertDialog.Builder builder=new AlertDialog.Builder(v.getContext());
                View view=LayoutInflater.from(mcontext).inflate(R.layout.custom_dialoq_view,null);

                _title = myList.get(position);

                radioGroup = view.findViewById(R.id.radio_group);
                button_dialog_ok = view.findViewById(R.id.button_dialog_ok);
                editText_note = view.findViewById(R.id.editText_note);
                textView_title = view.findViewById(R.id.textView_title);



                textView_title.setText(_title);

                builder.setView(view);

                final AlertDialog alertDialog=builder.create();
                alertDialog.show();

                button_dialog_ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view)
                    {

                        String note = editText_note.getText().toString();

                        int idx = 0;
                        int radioButtonID = radioGroup.getCheckedRadioButtonId();
                        if(radioButtonID!=-1) {
                            View radioButton = radioGroup.findViewById(radioButtonID);
                            idx = radioGroup.indexOfChild(radioButton);
                            RadioButton r = (RadioButton) radioGroup.getChildAt(idx);
                            String status = r.getText().toString();

                            /* Toast.makeText(mcontext.getApplicationContext(), "Title : "+_title+"\n \n "+"Note : "+editText_note.getText().toString()+" " +
                                      "\n \n Status : "+status, Toast.LENGTH_SHORT).show(); */

                            holder.imageView.setImageResource(R.drawable.ic_baseline_check_24);
                            alertDialog.cancel();

                        }
                        else
                        { Toast.makeText(mcontext.getApplicationContext(), "Nothing Checked", Toast.LENGTH_SHORT).show(); }

                    }
                });

            }
        });

    }


    @Override
    public int getItemCount()
    {
        return myList.size();
    }


    /******************************************************** Holder CLASS ************************************************************************/

    class CostomViewHolder extends RecyclerView.ViewHolder
    {

        TextView textView_title;
        ImageView imageView;
        LinearLayout linear_row;


        public CostomViewHolder(@NonNull View itemView)
        {
            super(itemView);

            textView_title = itemView.findViewById(R.id.textViev_list_title);
            imageView = itemView.findViewById(R.id.imageView_verify);
            linear_row = itemView.findViewById(R.id.linear_row);

        }

    }

}
