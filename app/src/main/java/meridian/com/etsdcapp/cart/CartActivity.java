//package meridian.com.etsdcapp.cart;
//
//import android.content.Context;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteException;
//import android.graphics.Typeface;
//import android.inputmethodservice.Keyboard;
//import android.support.v7.app.AlertDialog;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.support.v7.widget.Toolbar;
//import android.text.style.WrapTogetherSpan;
//import android.view.Gravity;
//import android.view.MenuItem;
//import android.view.View;
//import android.view.WindowManager;
//import android.widget.Button;
//import android.widget.ImageButton;
//import android.widget.TableLayout;
//import android.widget.TableRow;
//import android.widget.TextView;
//
//import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatchConfigImpl;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//import meridian.com.etsdcapp.R;
//
//import meridian.com.etsdcapp.adapter.SubCourseAdapterNew;
//import meridian.com.etsdcapp.course.CourseRegistrationActivity;
//import meridian.com.etsdcapp.course.MainCoursesActivity;
//import meridian.com.etsdcapp.course.SubCoursesActivity1;
//import meridian.com.etsdcapp.model.CartModel;
//
//public class CartActivity extends AppCompatActivity implements View.OnClickListener {
//    //final DataHelper dataHelper = new DataHelper(getApplicationContext());
//    String name, maincourseid;
//    int qty;
//    ArrayList<CartModel> crs_cart=new ArrayList<>();
//    int tot, cart_tot = 0;
//    int price;
//  Context context = this;
//
//    SQLiteDatabase db;
//    TextView tv_tot, grand;
//    String cart;
//
//    Button chkout;
//    ImageButton checkout;
//    String crcname,appndstrng;
//
//    CartModel cartmodl=new CartModel();
//   // ArrayList<String> arrcrc=new ArrayList<>();
//    int _course_ID;
//    final DataHelper dataHelper = new DataHelper(context);
//
// public TableRow row,row1,row2;
//  static   String _subcourse,  _subcourseid ,maincourse_id;
//    ArrayList<CartModel> arrayListcart=new ArrayList<>();
//    static  int checknotif;
// static    int checkcount;
//    static ArrayList<String> wordList;
// static ArrayList<String> wordList1;
//  static   String crc_list[],crc_list1[];
//    static  int count=0;
//    int e;
// TableLayout tableLayout1;
//    // TableLayout    tableLayout2;
//   // TableLayout tableLayout2;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        setContentView(R.layout.activity_cart);
//        chkout= (Button) findViewById(R.id.but_chkout);
//        chkout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent in = new Intent(getApplicationContext(), CourseRegistrationActivity.class);
//                startActivity(in);
//            }
//        });
//        Bundle extras = getIntent().getExtras();
//      final   CartModel cartModel=new CartModel();
//        if (extras != null) {
//            maincourse_id = extras.getString("id");
//            crcname = extras.getString("selctedcourse");
//            appndstrng=extras.getString("append_selsubcrc");
//       //     arrcrc = extras.getStringArrayList("arrcrc");
//        }
//
//        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_topcrt);
//        setSupportActionBar(toolbar);
//
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//
//        SharedPreferences sharedPref1 =getSharedPreferences("countnum",Context.MODE_PRIVATE);
//        count=sharedPref1.getInt("count",0);
//
//
//
//
//     tableLayout1= (TableLayout) findViewById(R.id.tablcart);
//
//
//
//       // RecyclerView rv= (RecyclerView) findViewById(R.id.rvcart);
//        cartmodl.setCrcnam(crcname);
//     //   cartmodl.setArrcrt(arrcrc);
//
//        crs_cart.add(cartmodl);
//
//       db = dataHelper.getReadableDatabase();
//       db.beginTransaction();
//        try {
//
//            String selectQuery =  "SELECT  * FROM " +DataHelper.TABLE_COURSE  ;
//            System.out.println("" + selectQuery);
//            Cursor cursor = db.rawQuery(selectQuery, null);
//
//            if (cursor.getCount() > 0)
//            {
//                while (cursor.moveToNext()) {
//
//                    _course_ID = cursor.getInt(cursor.getColumnIndex("_course_ID"));
//                    final String _course = cursor.getString(cursor.getColumnIndex("_course"));
//                maincourseid = cursor.getString(cursor.getColumnIndex("maincourseid"));
//
//       _subcourse = cursor.getString(cursor.getColumnIndex("_subcourse"));
//          _subcourseid = cursor.getString(cursor.getColumnIndex("_subcourseid"));
//                    System.out.println("cart_courseid" + _course_ID);
//                    System.out.println("cart_course" + _course);
//                    System.out.println("cart_sub_course" + _subcourse);
//                    System.out.println("cart_maincourseid" +maincourseid);
//                    System.out.println("cart_subcourseid" +_subcourseid);
//
//                    cartmodl.setCrcnam(_course);
//                    cartmodl.setCourseid(maincourseid);
//                    cartmodl.setSubcrcnam(_subcourse);
//                    cartmodl.setSubcourseid(_subcourseid);
//                    arrayListcart.add(cartmodl);
////                    TableLayout.LayoutParams tableLayoutParams = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,TableLayout.LayoutParams.WRAP_CONTENT);
////                    tableLayout2  = new TableLayout(this);
////                    tableLayout2.setId(_course_ID);
////                    tableLayout2.setPadding(20,0,20,0);
////                    tableLayout2.setLayoutParams(tableLayoutParams);
//
//                    if(_course!=null &&_subcourse!=null) {
//
//                        row = new TableRow(context);
//                        row.setId(Integer.parseInt(maincourseid));
//                        TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
//                        row.setLayoutParams(lp);
//
//                        TextView tv = new TextView(context);
//                        tv.setTextColor(getResources().getColor(R.color.White));
//                        tv.setTextSize(15);
//                        tv.setTypeface(null, Typeface.BOLD);
//
//
//                        row.setBackgroundResource(R.drawable.crossbutton);
//
//                        tv.setText("\t" + _course);
//
//                        row.addView(tv);
//
//
//                        //tableLayout1.addView(row);
//
//
//                        tableLayout1.addView(row);
//                        row.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(final View v) {
//                                final android.support.v7.app.AlertDialog alertDialog = new android.support.v7.app.AlertDialog.Builder(CartActivity.this, R.style.dialog).create();
//
//
//                                alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "yes", new DialogInterface.OnClickListener() {
//                                    @Override
//                                    public void onClick(DialogInterface dialog, int which) {
//                                        int s = 0;
//                                        dataHelper.delete(_course);
//                                        System.out.println();
//                                        //  row.findViewById(Integer.parseInt(maincourseid));
//                                        //    String k= String.valueOf(row.getChildAt(which));
//
//                                        // if( v instanceof row
//
//                                        String crc_list[] = _subcourse.split(",");
//                                        //  System.out.println("cart_subcrc_length1"+k);
//
//                                        s = crc_list.length;
//                                        System.out.println("cart_subcrc_length" + s);
//                                        int cntsub = 0;
//                                        SharedPreferences sharedPref = getSharedPreferences("countnum", Context.MODE_PRIVATE);
//                                        count = sharedPref.getInt("count", 0);
//                                        System.out.println("cart_count" + count);
//                                        count = count - s;
//                                        System.out.println("cart_countsubtractd" + count);
//                                        SharedPreferences.Editor editor = sharedPref.edit();
//                                        editor.putInt("count", count);
//                                        editor.commit();
//
//                                        SubCoursesActivity1.notif.setText("" + count);
//                                        checkcount = cartModel.getCheckcount();
//                                        checkcount = checkcount - 1;
//                                        cartModel.setCheckcount(checkcount);
//                                        Intent i = getIntent();
//                                        finish();
//                                        startActivity(i);
//
//
//                                    }
//                                });
//                                alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "No", new DialogInterface.OnClickListener() {
//                                    @Override
//                                    public void onClick(DialogInterface dialog, int which) {
//                                        alertDialog.dismiss();
//                                    }
//                                });
//
//
//                                alertDialog.show();
//                                alertDialog.setTitle("Delete this Course?");
//
//                                Button nbutton = alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE);
//                                //    nbutton.setBackgroundColor(context.getResources().getColor(R.color.butnbakcolr));
//                                nbutton.setTextColor(context.getResources().getColor(R.color.butnbakcolr));
//
//                                Button pbutton = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
//                                //   pbutton.setBackgroundColor(context.getResources().getColor(R.color.butnbakcolr));
//                                pbutton.setTextColor(context.getResources().getColor(R.color.butnbakcolr));
//
//
//                            }
//                        });
//
//
//                  crc_list = _subcourse.split(",");
//                 crc_list1 = _subcourseid.split(",");
//
//
//                        wordList= new ArrayList<String>(Arrays.asList(crc_list));
//
//                       wordList1 = new ArrayList<String>(Arrays.asList(crc_list1));
//                       int e;
//                        for (e=0;e<wordList.size();e++) {
//
//
//                            row1 = new TableRow(context);
//                            TableRow.LayoutParams lp1 = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);
//                            System.out.println("cart_courselength" + crc_list.length);
//                            if (wordList.size() <= wordList1.size())
//                                row1.setId(e);
//
//                            row1.setLayoutParams(lp1);
//                            row1.setGravity(Gravity.CENTER_HORIZONTAL);
//                            TextView tv1 = new TextView(context);
//                            tv1.setTextSize(12);
//                            tv1.setText(wordList.get(e));
//
//
//                            tv1.setTextColor(getResources().getColor(R.color.White));
//
//
//                            row1.addView(tv1);
//                            row1.setBackgroundResource(R.drawable.subcrc_bak);
//                            row1.setOnClickListener(CartActivity.this);
//                            tableLayout1.addView(row1);
//                        }
//
//
//                                // TableRow s=(TableRow)findViewById(row1.getId());
//                                //  System.out.println("cart_rows"+s);
//
////                            row1.setOnClickListener(new View.OnClickListener() {
////                                    @Override
////                                    public void onClick(View v) {
////
////
////
////                                    }
////                                });
//
//
//
//
//                                // tableLayout1.addView(row1);
//
//
//                            row2 = new TableRow(context);
//                            TableRow.LayoutParams lp1 = new TableRow.LayoutParams(2);
//                            row2.setBackgroundResource(R.drawable.transcart);
//                            row2.setLayoutParams(lp1);
//                            tv.setTextColor(getResources().getColor(R.color.White));
//                            row2.setGravity(Gravity.CENTER_HORIZONTAL);
//                            tableLayout1.addView(row2);
//                            System.out.println("cart_tableLayout2" + tableLayout1.getId());
////                            tableLayout1.addView(tableLayout1);
//
//                        }
//                    }
//
//                }
//
//
//
//
//
//
//
//            db.setTransactionSuccessful();
//
//        } catch (SQLiteException e) {
//            e.printStackTrace();
//
//        } finally {
//            db.endTransaction();
//            // End the transaction.
//            db.close();
//            // Close database
//
//
//        }
//
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
////        String status;
////
////        SharedPreferences sharedPref =getSharedPreferences("sharedstatus",Context.MODE_PRIVATE);
////        status=  sharedPref.getString("status",null);
////        if(status.contentEquals(null))
////        {   Intent i = getIntent();
////            finish();
////            startActivity(i);
////        }else
////        {
////            dataHelper.removeAll();
////            Intent i = getIntent();
////            finish();
////            startActivity(i);
////        }
//
//
//
//    }
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//
//        if (id == android.R.id.home) {
//
//
//            super.onBackPressed();
//            return true;
//        }
//
//
//        return super.onOptionsItemSelected(item);
//    }
//
//    @Override
//    public void onClick(View v) {
//        final int clicked_id = v.getId();
//        final android.support.v7.app.AlertDialog alertDialog = new android.support.v7.app.AlertDialog.Builder(CartActivity.this, R.style.dialog).create();
//        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "yes", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//
//                tableLayout1.removeView(row1);
//
//                System.out.println("cart_courselength" + crc_list.length);
//                System.out.println("cart_courselength1" + crc_list1.length);
//                if(wordList.size()>0) {
//
//                    wordList.remove(e);
//                    wordList1.remove(e);
//
//                }
//
//
//
//
//                SharedPreferences sharedPref = getSharedPreferences("countnum", Context.MODE_PRIVATE);
//                count = sharedPref.getInt("count", 0);
//                System.out.println("cart_count" + count);
//                count = count - 1;
//                System.out.println("cart_countsubtractd" + count);
//                SharedPreferences.Editor editor = sharedPref.edit();
//                editor.putInt("count", count);
//                editor.commit();
//
//                SubCoursesActivity1.notif.setText("" + count);
//
//                StringBuilder buffer = new StringBuilder();
//                StringBuilder buffer1 = new StringBuilder();
//                boolean processedFirst = false, processedFirst1 = false;
//                String firstParam = null, secondParam = null;
//
//                try {
//                    for (String record : wordList) {
//                        if (processedFirst)
//                            buffer.append(",");
//                        buffer.append(record);
//                        processedFirst = true;
//                    }
//                    firstParam = buffer.toString();
//                } finally {
//                    buffer = null;
//                }
//                processedFirst1 = false;
//                try {
//                    for (String record1 : wordList1) {
//                        if (processedFirst1)
//                            buffer1.append(",");
//                        buffer1.append(record1);
//                        processedFirst1 = true;
//                    }
//                    secondParam = buffer1.toString();
//                } finally {
//                    buffer1 = null;
//                }
//                processedFirst1 = false;
//
//                //  String s= String.valueOf(tableLayout2.getId());
//                // String s1= String.valueOf(row.getId());
//                // String p= String.valueOf(tableLayout2.getChildAt(0));
//                String p = String.valueOf(row.getId());
//                //   TextView firstTextView = (TextView) row1.getChildAt(0);
//                TextView secondTextView = (TextView) tableLayout1.getChildAt(0);
//                //    String firstText = firstTextView.getText().toString();
//                String secondText = secondTextView.getText().toString().trim();
//                // System.out.println("cart_firsttext" + firstText);
//                System.out.println("cart_updatedcourse" + firstParam);
//                System.out.println("cart_maincourserow_id" + p);
//                System.out.println("cart_maincoursename" + secondText);
//
//                System.out.println("cart_databsemaincrcid" + maincourseid);
//                dataHelper.getWritableDatabase();
//                System.out.println("  "+p+"  "+secondText+"  "+firstParam+"  "+secondParam);
//                dataHelper.close();
//
//                dataHelper.getWritableDatabase();
//                dataHelper.updateCourse(p, secondText, firstParam, secondParam);
//
//                Intent i = getIntent();
//                finish();
//                startActivity(i);
//
//
//            }
//        });
//        alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "No", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                alertDialog.dismiss();
//            }
//        });
//
//
//        alertDialog.show();
//        alertDialog.setTitle("Delete this SubCourse?");
//
//        Button nbutton = alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE);
//        //    nbutton.setBackgroundColor(context.getResources().getColor(R.color.butnbakcolr));
//        nbutton.setTextColor(context.getResources().getColor(R.color.butnbakcolr));
//
//        Button pbutton = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
//        //   pbutton.setBackgroundColor(context.getResources().getColor(R.color.butnbakcolr));
//        pbutton.setTextColor(context.getResources().getColor(R.color.butnbakcolr));
//
//    }
//}
//
