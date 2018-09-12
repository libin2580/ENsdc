package meridian.com.etsdcapp.cart;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.graphics.Color;
import android.graphics.Typeface;
import android.inputmethodservice.Keyboard;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;
import com.github.javiersantos.materialstyleddialogs.enums.Style;
import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;

import java.util.ArrayList;
import java.util.Arrays;

import cn.pedant.SweetAlert.SweetAlertDialog;
import meridian.com.etsdcapp.R;
import meridian.com.etsdcapp.adapter.SubCourseAdapterNew;
import meridian.com.etsdcapp.course.CourseRegistrationActivity;
import meridian.com.etsdcapp.course.SubCoursesActivity1;
import meridian.com.etsdcapp.login.LoginActivity;
import meridian.com.etsdcapp.model.CartModel;

public class CartActivityNews extends AppCompatActivity {
    String name, maincourseid;
    int qty;
    ArrayList<CartModel> crs_cart=new ArrayList<>();
    int tot, cart_tot = 0;
    int price;
    Context context = this;

    SQLiteDatabase db;
    TextView tv_tot, grand;
    String cart;

    Button chkout;
    ImageButton checkout;
    String crcname,appndstrng;
    int counts=0;


    // ArrayList<String> arrcrc=new ArrayList<>();
    int _course_ID;
    final DataHelper dataHelper = new DataHelper(context);


    static   String _subcourse,  _subcourseid ,maincourse_id,_course;
    ArrayList<CartModel> arrayListcart=new ArrayList<>();
    static  int checknotif;
    static    int checkcount;


    static  int count=0;
    int i;
    TableLayout tableLayout1;
    int  clicked_idrow;
    String userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        setContentView(R.layout.activity_cart);
        chkout = (Button) findViewById(R.id.but_chkout);
        SharedPreferences preferences= getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        userid = preferences.getString("userid", null);
        chkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userid!=null)
                {Intent in = new Intent(getApplicationContext(), CourseRegistrationActivity.class);
                    startActivity(in);

                }
                else {

                    final SweetAlertDialog dialog = new SweetAlertDialog(CartActivityNews.this,SweetAlertDialog.NORMAL_TYPE);
                    dialog.setTitleText("Alert!")
                            .setContentText("Please Login to Register For this Course")
                            .setConfirmText("OK")
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {
                                    dialog.dismiss();
                                }
                            })
                            .show();

                    dialog.findViewById(R.id.confirm_button).setBackgroundColor(Color.parseColor("#10315a"));

                  /*  new MaterialStyledDialog.Builder(CartActivityNews.this)
                            // .setTitle("SUCCESS!")
                            .setDescription("Please Login to Register For this Course")
                            .setTitle("Alert")
                            .setStyle(Style.HEADER_WITH_TITLE)
                            // .withDialogAnimation(true)
                            .setHeaderColor(R.color.butnbakcolr)
                            .setCancelable(true)
                            .setPositive(getResources().getString(R.string.yes), new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

                                }
                            })

                            .setNegative(getResources().getString(R.string.no), new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                    dialog.dismiss();
                                }
                            })
                            .show();
*/
                }

            }
        });
        Bundle extras = getIntent().getExtras();
        final CartModel cartModel = new CartModel();
        if (extras != null) {
            maincourse_id = extras.getString("id");
            crcname = extras.getString("selctedcourse");
            appndstrng = extras.getString("append_selsubcrc");
            counts = extras.getInt("count");
            //     arrcrc = extras.getStringArrayList("arrcrc");
        }

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_topcrt);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        SharedPreferences sharedPref1 = getSharedPreferences("countnum", Context.MODE_PRIVATE);
        count = sharedPref1.getInt("count", 0);

        if(count==0)
        {
            chkout.setVisibility(View.GONE);

            final SweetAlertDialog dialog = new SweetAlertDialog(this,SweetAlertDialog.NORMAL_TYPE);
            dialog.setTitleText("Alert!")
                    .setContentText("Your Cart is empty")
                    .setConfirmText("OK")
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            dialog.dismiss();
                            Intent i=new Intent(getApplicationContext(),SubCoursesActivity1.class);
                            startActivity(i);

                        }
                    })
                    .show();
            dialog.findViewById(R.id.confirm_button).setBackgroundColor(Color.parseColor("#10315a"));

         /*   final android.support.v7.app.AlertDialog alertDialog = new android.support.v7.app.AlertDialog.Builder(CartActivityNews.this).create();
            alertDialog.setTitle("Alert");
            alertDialog.setMessage("Your Cart is empty");


            alertDialog.setButton(DialogInterface.BUTTON_NEUTRAL, "ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    dialog.dismiss();
                    Intent i=new Intent(getApplicationContext(),SubCoursesActivity1.class);
                    startActivity(i);


                }
            });
            alertDialog.show();
            Button nbutton = alertDialog.getButton(DialogInterface.BUTTON_NEUTRAL);
            nbutton.setTextColor(getResources().getColor(R.color.Orange));
*/
        }
        else {
            chkout.setVisibility(View.VISIBLE);
        }

        tableLayout1 = (TableLayout) findViewById(R.id.tablcart);




        db = dataHelper.getReadableDatabase();
        db.beginTransaction();
        try {

            String selectQuery = "SELECT  * FROM " + DataHelper.TABLE_COURSE;
            System.out.println("" + selectQuery);
            Cursor cursor = db.rawQuery(selectQuery, null);

            if (cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    CartModel cartmodl=new CartModel();

                    _course_ID = cursor.getInt(cursor.getColumnIndex("_course_ID"));
                    _course = cursor.getString(cursor.getColumnIndex("_course"));
                    maincourseid = cursor.getString(cursor.getColumnIndex("maincourseid"));

                    _subcourse = cursor.getString(cursor.getColumnIndex("_subcourse"));
                    _subcourseid = cursor.getString(cursor.getColumnIndex("_subcourseid"));
                    System.out.println("cart_courseid" + _course_ID);
                    System.out.println("cart_maincourseid" + maincourseid);
                    System.out.println("cart_course" + _course);
                    System.out.println("cart_sub_course" + _subcourse);
                    System.out.println("cart_subcourseid" + _subcourseid);

                    cartmodl.setCrcnam(_course);
                    cartmodl.setCourseid(maincourseid);
                    cartmodl.setSubcrcnam(_subcourse);
                    cartmodl.setSubcourseid(_subcourseid);
                    arrayListcart.add(cartmodl);

                }
            }
        }
        catch(SQLiteException e)
        {
            e.printStackTrace();

        }finally
        {
            db.endTransaction();
            // End the transaction.
            db.close();
            // Close database


        }

//        for(int k=0;k<arrayListcart.size();k++) {
//              System.o
//        }



        for ( i = 0; i < arrayListcart.size(); i++)
        {
            final    TableRow    row = new TableRow(context);
            System.out.println("tablelayoutchild"+tableLayout1.getChildCount());
            System.out.println("rowchild"+row.getChildCount());
            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,TableRow.LayoutParams.WRAP_CONTENT);
            row.setLayoutParams(lp);
            row.setId(i);
            TextView tv = new TextView(context);
            tv.setTextColor(getResources().getColor(R.color.White));
            tv.setTextSize(15);
            tv.setTypeface(null, Typeface.BOLD);


            row.setBackgroundResource(R.color.cart_crc);

            tv.setText(arrayListcart.get(i).getCrcnam());
            tv.setPadding(5,0,5,0);
            row.addView(tv);


            //tableLayout1.addView(row);


            tableLayout1.addView(row);
//                    row.setOnClickListener(new View.OnClickListener() {
//                                               @Override
//                                               public void onClick(final View v) {
//                                                   final android.support.v7.app.AlertDialog alertDialog = new android.support.v7.app.AlertDialog.Builder(CartActivityNews.this, R.style.dialog).create();
//
//
//                                                   alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "yes", new DialogInterface.OnClickListener() {
//                                                       @Override
//                                                       public void onClick(DialogInterface dialog, int which) {
//                                                           int s = 0;
//                                                            clicked_idrow = v.getId();
//
//                                                           System.out.println("index1(i)"+i);
//                                                           System.out.println("index(which)"+i);
//                                                           dataHelper.delete(arrayListcart.get(clicked_idrow).getCrcnam());
//                                                           tableLayout1.removeView(row);
//                                                           System.out.println();
//                                                           //  row.findViewById(Integer.parseInt(maincourseid));
//                                                           //    String k= String.valueOf(row.getChildAt(which));
//
//                                                           // if( v instanceof row
//
//                                                           String crc_list[] = _subcourse.split(",");
//                                                           //  System.out.println("cart_subcrc_length1"+k);
//
//                                                           s = crc_list.length;
//                                                           System.out.println("cart_subcrc_length" + s);
//                                                           int cntsub = 0;
//                                                           SharedPreferences sharedPref = getSharedPreferences("countnum", Context.MODE_PRIVATE);
//                                                           count = sharedPref.getInt("count", 0);
//                                                           System.out.println("cart_count" + count);
//                                                           count = count - s;
//                                                           System.out.println("cart_countsubtractd" + count);
//                                                           SharedPreferences.Editor editor = sharedPref.edit();
//                                                           editor.putInt("count", count);
//                                                           editor.commit();
//
//                                                           SubCoursesActivity1.notif.setText("" + count);
//                                                           checkcount = cartModel.getCheckcount();
//                                                           checkcount = checkcount - 1;
//                                                           cartModel.setCheckcount(checkcount);
//                                                           Intent i = getIntent();
//                                                           finish();
//                                                           startActivity(i);
//
//
//                                                       }
//                                                   });
//                                                   alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "No", new DialogInterface.OnClickListener() {
//                                                       @Override
//                                                       public void onClick(DialogInterface dialog, int which) {
//                                                           alertDialog.dismiss();
//                                                       }
//                                                   });
//
//
//                                                   alertDialog.show();
//                                                   alertDialog.setTitle("Delete this Course?");
//
//                                                   Button nbutton = alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE);
//                                                   //    nbutton.setBackgroundColor(context.getResources().getColor(R.color.butnbakcolr));
//                                                   nbutton.setTextColor(context.getResources().getColor(R.color.butnbakcolr));
//
//                                                   Button pbutton = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
//                                                   //   pbutton.setBackgroundColor(context.getResources().getColor(R.color.butnbakcolr));
//                                                   pbutton.setTextColor(context.getResources().getColor(R.color.butnbakcolr));
//
//                                               }
//                                           });



            final String  crc_list[] = arrayListcart.get(i).getSubcrcnam().split("%");
            final String crc_list1[] = arrayListcart.get(i).getSubcourseid().split("%");
            int e;

            final ArrayList<String>     wordList= new ArrayList<String>(Arrays.asList(crc_list));

            final ArrayList<String>    wordList1 = new ArrayList<String>(Arrays.asList(crc_list1));
            for ( e=0;e<wordList.size();e++)
            {



                final TableRow  row1 = new TableRow(context);
                row1.setTag(i);
                row1.setId(e);
                TableRow.LayoutParams lp1 = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);
                System.out.println("cart_courselength" + crc_list.length);

                if (wordList.size() <= wordList1.size())


                    row1.setLayoutParams(lp1);
                row1.setPadding(20,10,20,10);

                row1.setGravity(Gravity.CENTER_HORIZONTAL);
                TextView tv1 = new TextView(context);
                tv1.setSingleLine(false);
                tv1.setGravity(Gravity.CENTER_VERTICAL);
                tv1.setTextSize(11);
                tv1.setPadding(20,10,20,10);

                System.out.println("wordlistname"+wordList.get(e));
                tv1.setText(wordList.get(e));


                tv1.setTextColor(getResources().getColor(R.color.White));



                row1.addView(tv1);
                row1.setBackgroundResource(R.drawable.deletesubcourse1);

                tableLayout1.addView(row1);
                final int finalE = e;
                final int finalE1 = e;
                row1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final int clicked_id = v.getId();



                        final SweetAlertDialog dialog = new SweetAlertDialog(CartActivityNews.this,SweetAlertDialog.NORMAL_TYPE);
                        dialog.setTitleText(wordList.get(finalE1))
                                .setContentText("Remove this Course from cart?")
                                .setConfirmText("YES")
                                .setCancelText("NO")
                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sDialog) {
                                        int s= (int) row1.getTag();
                                        System.out.println("index1(which)"+s);
                                        System.out.println("clicked id)"+row1.getTag());

                                        tableLayout1.removeView(row1);
                                        System.out.println("cart_courselength" + crc_list.length);
                                        System.out.println("cart_courselength1" + crc_list1.length);
                                        wordList.remove(finalE);
                                        wordList1.remove(finalE);
                                        if(wordList.isEmpty())
                                        {
                                            dataHelper.delete(arrayListcart.get(s).getCrcnam());
                                            //tableLayout1.removeView(row);
                                        }
                                        SharedPreferences sharedPref = getSharedPreferences("countnum", Context.MODE_PRIVATE);
                                        count = sharedPref.getInt("count", 0);
                                        System.out.println("cart_count" + count);
                                        count = count - 1;
                                        System.out.println("cart_countsubtractd" + count);
                                        SharedPreferences.Editor editor = sharedPref.edit();
                                        editor.putInt("count", count);
                                        editor.commit();

                                        SubCoursesActivity1.notif.setText("" + count);
                                        StringBuilder buffer = new StringBuilder();
                                        StringBuilder buffer1 = new StringBuilder();
                                        boolean processedFirst = false, processedFirst1 = false;
                                        String firstParam = null, secondParam = null;

                                        try {
                                            for (String record : wordList) {
                                                if (processedFirst)
                                                    buffer.append("%");
                                                buffer.append(record);
                                                processedFirst = true;
                                            }
                                            firstParam = buffer.toString();
                                        } finally {
                                            buffer = null;
                                        }
                                        processedFirst1 = false;
                                        try {
                                            for (String record1 : wordList1) {
                                                if (processedFirst1)
                                                    buffer1.append("%");
                                                buffer1.append(record1);
                                                processedFirst1 = true;
                                            }
                                            secondParam = buffer1.toString();
                                        } finally {
                                            buffer1 = null;
                                        }
                                        processedFirst1 = false;
                                        System.out.println("index_subcourse"+clicked_idrow);
                                        int row_id=row.indexOfChild(row);


                                        String clickedmaincrc = arrayListcart.get(s).getCrcnam();
                                        System.out.println("clickedmaincrc"+clickedmaincrc);

                                        String clickedmaincr_id = arrayListcart.get(s).getCourseid();
                                        System.out.println("clickedmaincrcid"+clickedmaincr_id);
                                        dataHelper.getWritableDatabase();
                                        dataHelper.updateCourse(clickedmaincr_id, clickedmaincrc, firstParam, secondParam);
                                        Intent i = getIntent();
                                        finish();
                                        startActivity(i);


                                    }
                                })
                                .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener(){

                                    @Override
                                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                                        dialog.dismiss();
                                    }
                                })
                                .show();


                        dialog.findViewById(R.id.confirm_button).setBackgroundColor(Color.parseColor("#10315a"));
                        dialog.findViewById(R.id.cancel_button).setBackgroundColor(Color.parseColor("#10315a"));




                   /*     final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                                context);

                        // set title
                        alertDialogBuilder.setTitle(wordList.get(finalE1));

                        // set dialog message
                        alertDialogBuilder
                                .setMessage("Remove this Course from cart?");

                        alertDialogBuilder.setPositiveButton( "Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                int s= (int) row1.getTag();
                                System.out.println("index1(which)"+s);
                                System.out.println("clicked id)"+row1.getTag());

                                tableLayout1.removeView(row1);
                                System.out.println("cart_courselength" + crc_list.length);
                                System.out.println("cart_courselength1" + crc_list1.length);
                                wordList.remove(finalE);
                                wordList1.remove(finalE);
                                if(wordList.isEmpty())
                                {
                                    dataHelper.delete(arrayListcart.get(s).getCrcnam());
                                    //tableLayout1.removeView(row);
                                }
                                SharedPreferences sharedPref = getSharedPreferences("countnum", Context.MODE_PRIVATE);
                                count = sharedPref.getInt("count", 0);
                                System.out.println("cart_count" + count);
                                count = count - 1;
                                System.out.println("cart_countsubtractd" + count);
                                SharedPreferences.Editor editor = sharedPref.edit();
                                editor.putInt("count", count);
                                editor.commit();

                                SubCoursesActivity1.notif.setText("" + count);
                                StringBuilder buffer = new StringBuilder();
                                StringBuilder buffer1 = new StringBuilder();
                                boolean processedFirst = false, processedFirst1 = false;
                                String firstParam = null, secondParam = null;

                                try {
                                    for (String record : wordList) {
                                        if (processedFirst)
                                            buffer.append("%");
                                        buffer.append(record);
                                        processedFirst = true;
                                    }
                                    firstParam = buffer.toString();
                                } finally {
                                    buffer = null;
                                }
                                processedFirst1 = false;
                                try {
                                    for (String record1 : wordList1) {
                                        if (processedFirst1)
                                            buffer1.append("%");
                                        buffer1.append(record1);
                                        processedFirst1 = true;
                                    }
                                    secondParam = buffer1.toString();
                                } finally {
                                    buffer1 = null;
                                }
                                processedFirst1 = false;
                                System.out.println("index_subcourse"+clicked_idrow);
                                int row_id=row.indexOfChild(row);


                                String clickedmaincrc = arrayListcart.get(s).getCrcnam();
                                System.out.println("clickedmaincrc"+clickedmaincrc);

                                String clickedmaincr_id = arrayListcart.get(s).getCourseid();
                                System.out.println("clickedmaincrcid"+clickedmaincr_id);
                                dataHelper.getWritableDatabase();
                                dataHelper.updateCourse(clickedmaincr_id, clickedmaincrc, firstParam, secondParam);
                                Intent i = getIntent();
                                finish();
                                startActivity(i);


                            }
                        });
                        alertDialogBuilder.setNegativeButton( "No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });

                        AlertDialog alertDialog = alertDialogBuilder.create();
                        alertDialog.show();
                        // show it
                        //  TextView messageText = (TextView)alertDialog.findViewById(android.R.id.message);
                        // messageText.setGravity(Gravity.CENTER);

//                                        alertDialog.show();
//                                        TextView textView = (TextView)alertDialog.findViewById(android.R.id.title);
//                                        textView.setTextSize(15);
//                                        alertDialog.setTitle("Remove "+wordList.get(finalE1)+" Course from cart?");
                        //  alertDialog.show();
                        Window window = alertDialog.getWindow();
                        window.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);

                        Button nbutton = alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE);
                        //    nbutton.setBackgroundColor(context.getResources().getColor(R.color.butnbakcolr));
                        nbutton.setTextColor(context.getResources().getColor(R.color.butnbakcolr));

                        Button pbutton = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
                        //   pbutton.setBackgroundColor(context.getResources().getColor(R.color.butnbakcolr));
                        pbutton.setTextColor(context.getResources().getColor(R.color.butnbakcolr));*/


                    }
                });




            }



        }








    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        Intent i=new Intent(getApplicationContext(),SubCoursesActivity1.class);
        startActivity(i);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        if (id == android.R.id.home) {

            Intent i=new Intent(getApplicationContext(),SubCoursesActivity1.class);
            startActivity(i);

            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}