package meridian.com.etsdcapp.gallery;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cn.pedant.SweetAlert.SweetAlertDialog;
import meridian.com.etsdcapp.NetworkCheckingClass;

import meridian.com.etsdcapp.R;
import meridian.com.etsdcapp.RecyclerItemClickListener;
import meridian.com.etsdcapp.adapter.GalleryAdapter;
import meridian.com.etsdcapp.model.GalleryModel;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link GalleryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GalleryFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    ProgressBar progress;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    String description;
    String title;
    int id;
    RecyclerView recyclerView;
    ArrayList<GalleryModel> arrglry;

    private OnFragmentInteractionListener mListener;

    public GalleryFragment() {
        // Required empty public constructor
    }



    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GalleryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GalleryFragment newInstance(String param1, String param2) {
        GalleryFragment fragment = new GalleryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

     id = getArguments().getInt("id");


        System.out.println("iddd2"+id);
        View view = inflater.inflate(R.layout.fragment_galry, container, false);
        progress = (ProgressBar)view.findViewById(R.id.progress_bar);
        recyclerView = (RecyclerView) view.findViewById(R.id.dummyfraggal_scrollableview);
        //    GridLayoutManager llm ;
//            recyclerView.setLayoutManager(llm);
        System.out.println("iddd3"+id);

        NetworkCheckingClass networkCheckingClass=new NetworkCheckingClass(getContext());
        boolean i= networkCheckingClass.ckeckinternet();
        if(i==true) {
            progress.setVisibility(ProgressBar.VISIBLE);
        RequestQueue queue3 = Volley.newRequestQueue(getContext());
        String url3 = "http://www.app.etsdc.com/response.php?fid=5&album_id=" + id;

        StringRequest stringRequest3 = new StringRequest
                (Request.Method.GET, url3, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        //  tv.setText("Response is: "+ response);

                        System.out.println("++++++++++++++RESPONSE+++++++++++++++ gallery :" + response);


                        JSONArray jsonarray;
                        JSONObject jsonobject;
                        GalleryModel gm2;
                        try {

                            arrglry = new ArrayList<>();
                            jsonarray = new JSONArray(response);
                            System.out.println("g2f");
                            for (int i = 0; i < jsonarray.length(); i++) {
                                gm2 = new GalleryModel();
                                System.out.println("g3f");
                                jsonobject = jsonarray.getJSONObject(i);
                                String gal_id = jsonobject.getString("gal_id");
                                String image = jsonobject.getString("image");
                                title = jsonobject.getString("title");
                                description = jsonobject.getString("description");
                                String url2 = "http://www.app.etsdc.com/uploads/gallery/" + image;
                                gm2.setGalimag(url2);
                                // gm2.setGalimag(url2);
                                gm2.setGallerytitl(title);
                                gm2.setGallerydescrptn(description);
                                arrglry.add(gm2);


                            }
                            progress.setVisibility(ProgressBar.GONE);
                            //GalleryAdapter adapter0 = new GalleryAdapter(arrglry, getContext());
                            //adapter0.notifyDataSetChanged();

                            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
                            android.os.Handler handler=new android.os.Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {

                                    recyclerView.setAdapter(new GalleryAdapter(arrglry, getContext()));
                                    // Do something after 5s = 5000ms

                                }
                            }, 1000);;


                            recyclerView.setHasFixedSize(true);
                            recyclerView.addOnItemTouchListener(
                                    new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(View view, int position) {


                                            Intent in = new Intent(getActivity(), GalleryDtls.class);
                                            String galimg = arrglry.get(position).getGalimag();
                                            String  descriptions=arrglry.get(position).getGallerydescrptn();
                                            String  titles=arrglry.get(position).getGallerytitl();
                                            in.putExtra("url", galimg);
                                            in.putExtra("albumid", id);
                                            in.putExtra("description", descriptions);
                                            in.putExtra("title", titles);
                                            System.out.println("galryimag" + galimg);
                                            startActivity(in);
                                        }
                                    })
                            );


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }




                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //tv.setText("That didn't work!");

                    }
                });

        queue3.add(stringRequest3);

    }
    else {
            final SweetAlertDialog dialog = new SweetAlertDialog(getContext(),SweetAlertDialog.NORMAL_TYPE);
            dialog.setTitleText("Alert!")
                    .setContentText("Oops Your Connection Seems Off...")
                    .setConfirmText("OK")
                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            dialog.dismiss();
                        }
                    })
                    .show();


            dialog.findViewById(R.id.confirm_button).setBackgroundColor(Color.parseColor("#10315a"));

      /*  final android.support.v7.app.AlertDialog alertDialog = new android.support.v7.app.AlertDialog.Builder(getContext()).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("Oops Your Connection Seems Off..");

        alertDialog.setButton(DialogInterface.BUTTON_NEUTRAL, "yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();


            }
        });
        alertDialog.show();
            Button nbutton = alertDialog.getButton(DialogInterface.BUTTON_NEUTRAL);
            nbutton.setTextColor(getResources().getColor(R.color.Orange));*/
    }



        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
