package meridian.com.etsdcapp.sidebar;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import cn.pedant.SweetAlert.SweetAlertDialog;
import meridian.com.etsdcapp.R;
import meridian.com.etsdcapp.login.LoginActivity;

public class ContactUsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    LinearLayout laytel, layfax, layeml, laywats;
    ImageView imgtel, imgfax, imgeml, imgwats;
    private static final int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_contact_us);
        laytel = (LinearLayout) findViewById(R.id.lay_tel);
        layfax = (LinearLayout) findViewById(R.id.lay_fax);
        layeml = (LinearLayout) findViewById(R.id.lay_eml);
        laywats = (LinearLayout) findViewById(R.id.lay_wats);
        imgtel = (ImageView) findViewById(R.id.img_tel);
        imgfax = (ImageView) findViewById(R.id.img_fax);
        imgeml = (ImageView) findViewById(R.id.img_eml);
        imgwats = (ImageView) findViewById(R.id.img_wats);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_topc);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
//            @Override
//            public void onBackStackChanged() {
//                if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
//                  getActionBar().setDisplayHomeAsUpEnabled(true);
//                } else {
//                    getActionBar().setDisplayHomeAsUpEnabled(false);
//                }
//            }
//        });


//        getSupportFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
//            @Override
//            public void onBackStackChanged() {
//                if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
//                    ((ActionBarActivity)getApplicationContext()). getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//                } else {
//                    ((ActionBarActivity)getApplicationContext()). getSupportActionBar().setDisplayHomeAsUpEnabled(false);
//                }
//            }
//        });


        laytel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                laytel.setBackgroundResource(R.color.trans);
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:+971-2-555-2034"));
                startActivity(intent);

            }
        });
        layfax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layfax.setBackgroundResource(R.color.trans);

            }
        });
        layeml.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layeml.setBackgroundResource(R.color.trans);
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto", "enquiry@etsdc.com", null));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Enquire");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Body");
                //  emailIntent.putExtra(Intent.EXTRA_EMAIL, addresses);
                startActivity(Intent.createChooser(emailIntent, "Send email..."));
            }
        });
        laywats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED&& checkSelfPermission(Manifest.permission.WRITE_CONTACTS) != PackageManager.PERMISSION_GRANTED)
                {


                    requestPermissions();
                }else {

                    requestPermissions();
                    if (contactExists(getApplicationContext(), "+971529605389")) {
                        String ij = "+971529605389";
                        Uri uri = Uri.parse("smsto:" + ij);
                        try {
                            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                                // Running on something older than API level 11, so disable
                                // the drag/drop features that use ClipboardManager APIs

                                Intent i = new Intent(Intent.ACTION_SENDTO, uri);
                                i.putExtra("sms_body", "as sdj ajs");
                                i.setPackage("com.whatsapp");
                                startActivity(i);
                            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                PackageManager pm = getPackageManager();
                                try {

                                    Intent waIntent = new Intent(Intent.ACTION_SEND, uri);
                                    waIntent.setType("text/plain");
                                    String text = "YOUR TEXT HERE";

                                    PackageInfo info = pm.getPackageInfo("com.whatsapp", PackageManager.GET_META_DATA);
                                    //Check if package exists or not. If not then code
                                    //in catch block will be called
                                    waIntent.setPackage("com.whatsapp");

                                    waIntent.putExtra(Intent.EXTRA_TEXT, text);
                                    startActivity(Intent.createChooser(waIntent, "Share with"));

                                } catch (PackageManager.NameNotFoundException e) {
                                    Toast.makeText(getApplicationContext(), "WhatsApp not Installed", Toast.LENGTH_SHORT).show();
                                }
                            }


                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), "This Feature not currently available in your device", Toast.LENGTH_SHORT).show();
                        }

                    } else {


                        final SweetAlertDialog dialog = new SweetAlertDialog(ContactUsActivity.this,SweetAlertDialog.NORMAL_TYPE);
                        dialog.setTitleText("ETSDC Contact")
                                .setContentText("Please Save the no:+971529605389")
                                .setConfirmText("SAVE")
                                .setCancelText("CANCEL")
                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sDialog) {
                                        dialog.dismiss();
                                        String ij = "+971529605389";
                                        Uri uri = Uri.parse("smsto:" + ij);
                                        Intent intent = new Intent(Intent.ACTION_INSERT);
                                        intent.setType(ContactsContract.Contacts.CONTENT_TYPE);

                                        intent.putExtra(ContactsContract.Intents.Insert.NAME, "ETSDC");
                                        intent.putExtra(ContactsContract.Intents.Insert.PHONE, "+971529605389");
                                        intent.putExtra(ContactsContract.Intents.Insert.EMAIL, "enquiry@etsdc.com");
                                        startActivityForResult(intent, 1);

                                        try {
                                            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                                                // Running on something older than API level 11, so disable
                                                // the drag/drop features that use ClipboardManager APIs

                                                Intent i = new Intent(Intent.ACTION_SENDTO, uri);
                                                i.putExtra("sms_body", "as sdj ajs");
                                                i.setPackage("com.whatsapp");
                                                startActivity(i);
                                            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                                PackageManager pm = getPackageManager();
                                                try {

                                                    Intent waIntent = new Intent(Intent.ACTION_SEND, uri);
                                                    waIntent.setType("text/plain");
                                                    String text = "YOUR TEXT HERE";

                                                    PackageInfo info = pm.getPackageInfo("com.whatsapp", PackageManager.GET_META_DATA);
                                                    //Check if package exists or not. If not then code
                                                    //in catch block will be called
                                                    waIntent.setPackage("com.whatsapp");

                                                    waIntent.putExtra(Intent.EXTRA_TEXT, text);
                                                    startActivity(Intent.createChooser(waIntent, "Share with"));

                                                } catch (PackageManager.NameNotFoundException e) {
                                                    Toast.makeText(getApplicationContext(), "WhatsApp not Installed", Toast.LENGTH_SHORT).show();
                                                }
                                            }


                                        } catch (Exception e) {
                                            Toast.makeText(getApplicationContext(), "This Feature not currently available in your device", Toast.LENGTH_SHORT).show();
                                        }


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




                       /* final android.support.v7.app.AlertDialog alertDialog = new android.support.v7.app.AlertDialog.Builder(ContactUsActivity.this).create();
                        alertDialog.setTitle("ETSDC Contact");
                        alertDialog.setMessage("Please Save the no:+971529605389");

                        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "SAVE", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                dialog.dismiss();
                                String ij = "+971529605389";
                                Uri uri = Uri.parse("smsto:" + ij);
                                Intent intent = new Intent(Intent.ACTION_INSERT);
                                intent.setType(ContactsContract.Contacts.CONTENT_TYPE);

                                intent.putExtra(ContactsContract.Intents.Insert.NAME, "ETSDC");
                                intent.putExtra(ContactsContract.Intents.Insert.PHONE, "+971529605389");
                                intent.putExtra(ContactsContract.Intents.Insert.EMAIL, "enquiry@etsdc.com");
                                startActivityForResult(intent, 1);

                                try {
                                    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                                        // Running on something older than API level 11, so disable
                                        // the drag/drop features that use ClipboardManager APIs

                                        Intent i = new Intent(Intent.ACTION_SENDTO, uri);
                                        i.putExtra("sms_body", "as sdj ajs");
                                        i.setPackage("com.whatsapp");
                                        startActivity(i);
                                    } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                        PackageManager pm = getPackageManager();
                                        try {

                                            Intent waIntent = new Intent(Intent.ACTION_SEND, uri);
                                            waIntent.setType("text/plain");
                                            String text = "YOUR TEXT HERE";

                                            PackageInfo info = pm.getPackageInfo("com.whatsapp", PackageManager.GET_META_DATA);
                                            //Check if package exists or not. If not then code
                                            //in catch block will be called
                                            waIntent.setPackage("com.whatsapp");

                                            waIntent.putExtra(Intent.EXTRA_TEXT, text);
                                            startActivity(Intent.createChooser(waIntent, "Share with"));

                                        } catch (PackageManager.NameNotFoundException e) {
                                            Toast.makeText(getApplicationContext(), "WhatsApp not Installed", Toast.LENGTH_SHORT).show();
                                        }
                                    }


                                } catch (Exception e) {
                                    Toast.makeText(getApplicationContext(), "This Feature not currently available in your device", Toast.LENGTH_SHORT).show();
                                }


                            }
                        });
                        alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "CANCEL", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                dialog.dismiss();

                            }

                        });
                        alertDialog.show();
*/
                    }
                }



            }
        });

        imgtel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                laytel.setBackgroundResource(R.color.trans);
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:+971-2-555-2034"));
                startActivity(intent);
            }
        });
        imgfax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layfax.setBackgroundResource(R.color.trans);

            }
        });
        imgeml.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layeml.setBackgroundResource(R.color.trans);
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto", "enquiry@etsdc.com", null));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Enquire");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Body");
                //  emailIntent.putExtra(Intent.EXTRA_EMAIL, addresses);
                startActivity(Intent.createChooser(emailIntent, "Send email..."));
            }
        });
        imgwats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                laywats.setBackgroundResource(R.color.trans);

                if (ActivityCompat.checkSelfPermission(ContactUsActivity.this, Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_DENIED && ActivityCompat.checkSelfPermission(ContactUsActivity.this, Manifest.permission.WRITE_CONTACTS) == PackageManager.PERMISSION_DENIED) {

                    requestPermissions();
                }else

                {

                    if (contactExists(getApplicationContext(), "+971529605389")) {
                        String ij = "+971529605389";
                        Uri uri = Uri.parse("smsto:" + ij);
                        try {
                            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                                // Running on something older than API level 11, so disable
                                // the drag/drop features that use ClipboardManager APIs

                                Intent i = new Intent(Intent.ACTION_SENDTO, uri);
                                i.putExtra("sms_body", "as sdj ajs");
                                i.setPackage("com.whatsapp");
                                startActivity(i);
                            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                PackageManager pm = getPackageManager();
                                try {

                                    Intent waIntent = new Intent(Intent.ACTION_SEND, uri);
                                    waIntent.setType("text/plain");
                                    String text = "YOUR TEXT HERE";

                                    PackageInfo info = pm.getPackageInfo("com.whatsapp", PackageManager.GET_META_DATA);
                                    //Check if package exists or not. If not then code
                                    //in catch block will be called
                                    waIntent.setPackage("com.whatsapp");

                                    waIntent.putExtra(Intent.EXTRA_TEXT, text);
                                    startActivity(Intent.createChooser(waIntent, "Share with"));

                                } catch (PackageManager.NameNotFoundException e) {
                                    Toast.makeText(getApplicationContext(), "WhatsApp not Installed", Toast.LENGTH_SHORT).show();
                                }
                            }


                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), "This Feature not currently available in your device", Toast.LENGTH_SHORT).show();
                        }

                    } else {


                        final SweetAlertDialog dialog = new SweetAlertDialog(ContactUsActivity.this,SweetAlertDialog.NORMAL_TYPE);
                        dialog.setTitleText("ETSDC Contact")
                                .setContentText("Please Save the no:+971529605389")
                                .setConfirmText("SAVE")
                                .setCancelText("CANCEL")
                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener(){

                                    @Override
                                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                                        dialog.dismiss();
                                        String ij = "+971529605389";
                                        Uri uri = Uri.parse("smsto:" + ij);
                                        Intent intent = new Intent(Intent.ACTION_INSERT);
                                        intent.setType(ContactsContract.Contacts.CONTENT_TYPE);

                                        intent.putExtra(ContactsContract.Intents.Insert.NAME, "ETSDC");
                                        intent.putExtra(ContactsContract.Intents.Insert.PHONE, "+971529605389");
                                        intent.putExtra(ContactsContract.Intents.Insert.EMAIL, "enquiry@etsdc.com");
                                        startActivityForResult(intent, 1);

                                        try {
                                            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                                                // Running on something older than API level 11, so disable
                                                // the drag/drop features that use ClipboardManager APIs

                                                Intent i = new Intent(Intent.ACTION_SENDTO, uri);
                                                i.putExtra("sms_body", "as sdj ajs");
                                                i.setPackage("com.whatsapp");
                                                startActivity(i);
                                            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                                PackageManager pm = getPackageManager();
                                                try {

                                                    Intent waIntent = new Intent(Intent.ACTION_SEND, uri);
                                                    waIntent.setType("text/plain");
                                                    String text = "YOUR TEXT HERE";

                                                    PackageInfo info = pm.getPackageInfo("com.whatsapp", PackageManager.GET_META_DATA);
                                                    //Check if package exists or not. If not then code
                                                    //in catch block will be called
                                                    waIntent.setPackage("com.whatsapp");

                                                    waIntent.putExtra(Intent.EXTRA_TEXT, text);
                                                    startActivity(Intent.createChooser(waIntent, "Share with"));

                                                } catch (PackageManager.NameNotFoundException e) {
                                                    Toast.makeText(getApplicationContext(), "WhatsApp not Installed", Toast.LENGTH_SHORT).show();
                                                }
                                            }


                                        } catch (Exception e) {
                                            Toast.makeText(getApplicationContext(), "This Feature not currently available in your device", Toast.LENGTH_SHORT).show();
                                        }

                                    }
                                })
                                .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sDialog) {
                                        dialog.dismiss();
                                    }
                                })
                                .show();


                        dialog.findViewById(R.id.confirm_button).setBackgroundColor(Color.parseColor("#10315a"));
                        dialog.findViewById(R.id.cancel_button).setBackgroundColor(Color.parseColor("#10315a"));


                       /* final android.support.v7.app.AlertDialog alertDialog = new android.support.v7.app.AlertDialog.Builder(ContactUsActivity.this).create();
                        alertDialog.setTitle("ETSDC Contact");
                        alertDialog.setMessage("Please Save the no:+971529605389");

                        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "SAVE", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                dialog.dismiss();
                                String ij = "+971529605389";
                                Uri uri = Uri.parse("smsto:" + ij);
                                Intent intent = new Intent(Intent.ACTION_INSERT);
                                intent.setType(ContactsContract.Contacts.CONTENT_TYPE);

                                intent.putExtra(ContactsContract.Intents.Insert.NAME, "ETSDC");
                                intent.putExtra(ContactsContract.Intents.Insert.PHONE, "+971529605389");
                                intent.putExtra(ContactsContract.Intents.Insert.EMAIL, "enquiry@etsdc.com");
                                startActivityForResult(intent, 1);

                                try {
                                    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                                        // Running on something older than API level 11, so disable
                                        // the drag/drop features that use ClipboardManager APIs

                                        Intent i = new Intent(Intent.ACTION_SENDTO, uri);
                                        i.putExtra("sms_body", "as sdj ajs");
                                        i.setPackage("com.whatsapp");
                                        startActivity(i);
                                    } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                        PackageManager pm = getPackageManager();
                                        try {

                                            Intent waIntent = new Intent(Intent.ACTION_SEND, uri);
                                            waIntent.setType("text/plain");
                                            String text = "YOUR TEXT HERE";

                                            PackageInfo info = pm.getPackageInfo("com.whatsapp", PackageManager.GET_META_DATA);
                                            //Check if package exists or not. If not then code
                                            //in catch block will be called
                                            waIntent.setPackage("com.whatsapp");

                                            waIntent.putExtra(Intent.EXTRA_TEXT, text);
                                            startActivity(Intent.createChooser(waIntent, "Share with"));

                                        } catch (PackageManager.NameNotFoundException e) {
                                            Toast.makeText(getApplicationContext(), "WhatsApp not Installed", Toast.LENGTH_SHORT).show();
                                        }
                                    }


                                } catch (Exception e) {
                                    Toast.makeText(getApplicationContext(), "This Feature not currently available in your device", Toast.LENGTH_SHORT).show();
                                }


                            }
                        });
                        alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "CANCEL", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                dialog.dismiss();

                            }

                        });
                        alertDialog.show();
*/
                    }


                }
            }
        });


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(24.382801, 54.514655), 10.0f));
        LatLng sydney = new LatLng(24.382801, 54.514655);
        mMap.addMarker(new MarkerOptions().position(sydney).title("ETSDC"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    @Override
    protected void onResume() {
        super.onResume();
        laytel.setBackgroundResource(R.color.boxtrans);
        layfax.setBackgroundResource(R.color.boxtrans);
        layeml.setBackgroundResource(R.color.boxtrans);
        laywats.setBackgroundResource(R.color.boxtrans);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        laytel.setBackgroundResource(R.color.boxtrans);
        layfax.setBackgroundResource(R.color.boxtrans);
        layeml.setBackgroundResource(R.color.boxtrans);
        laywats.setBackgroundResource(R.color.boxtrans);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        if (id == android.R.id.home) {


            super.onBackPressed();
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 1:
                    if (requestCode == 1) {

                    }
                    super.onActivityResult(requestCode, resultCode, data);
            }
        }
    }

    public boolean contactExists(Context context, String number) {
/// number is the phone number
        Uri lookupUri = Uri.withAppendedPath(
                ContactsContract.PhoneLookup.CONTENT_FILTER_URI,
                Uri.encode(number));
        String[] mPhoneNumberProjection = {ContactsContract.PhoneLookup._ID, ContactsContract.PhoneLookup.NUMBER, ContactsContract.PhoneLookup.DISPLAY_NAME};
        Cursor cur = context.getContentResolver().query(lookupUri, mPhoneNumberProjection, null, null, null);
        try {
            if (cur.moveToFirst()) {
                return true;
            }
        } finally {
            if (cur != null)
                cur.close();
        }
        return false;
    }

    private void requestPermissions() {
        ActivityCompat.requestPermissions(ContactUsActivity.this,
                new String[]{Manifest.permission.READ_CONTACTS,Manifest.permission.WRITE_CONTACTS},
                REQUEST_CODE);
        // ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {


                    Toast.makeText(this, "PERMISSION GRANTED", Toast.LENGTH_SHORT).show();


//                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                        shouldShowRequestPermissionRationale(Manifest.permission.RECORD_AUDIO);
//                    }


                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.

                    Toast.makeText(this, "PERMISSION NOT GRANTED", Toast.LENGTH_SHORT).show();
                    finish();

                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }
}