package meridian.com.etsdcapp.library;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
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

import meridian.com.etsdcapp.MainActivity;
import meridian.com.etsdcapp.R;
import meridian.com.etsdcapp.RecyclerItemClickListener;
import meridian.com.etsdcapp.adapter.LibraryAdapter;
import meridian.com.etsdcapp.course.MainCoursesActivity;
import meridian.com.etsdcapp.model.LibraryModel;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LibraryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LibraryFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    ProgressBar progress;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    ProgressDialog pd;
    int color;
    int sel;
    int id;
    RecyclerView recyclerView;
   ArrayList<LibraryModel> liblist;

    private OnFragmentInteractionListener mListener;

    public LibraryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LibraryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LibraryFragment newInstance(String param1, String param2) {
        LibraryFragment fragment = new LibraryFragment();
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




        View view = inflater.inflate(R.layout.fragment_library, container, false);
        progress = (ProgressBar)view.findViewById(R.id.progress_bar);
        progress.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                progress.setVisibility(View.INVISIBLE);
                return false;
            }
        });
        recyclerView = (RecyclerView) view.findViewById(R.id.dummyfrag_scrollableview_lib);
        GridLayoutManager llm = new GridLayoutManager(getContext(), 3);
        recyclerView.setLayoutManager(llm);
        recyclerView.setHasFixedSize(true);


//        pd = new ProgressDialog(getActivity());
//        pd.setTitle("Loading...");
//        pd.setMessage("Please wait...");
//        pd.setCancelable(true);
//        pd.show();
        progress.setVisibility(ProgressBar.VISIBLE);
        RequestQueue queue3 = Volley.newRequestQueue(getContext());
        String url3 = "http://www.app.etsdc.com/response.php?fid=10";

        StringRequest stringRequest3 = new StringRequest
                (Request.Method.GET, url3, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        //  tv.setText("Response is: "+ response);
//pd.dismiss();
                        System.out.println("++++++++++++++RESPONSE+++++++++++++++ gallery :" + response);
                        if (response != null) {
                            if (response.equals("\"failed\"")) {


//
                            }
                            else {
                                JSONArray jsonarray;
                                JSONObject jsonobject;
                                LibraryModel lb;
                                try {

                                    liblist = new ArrayList<>();
                                    jsonarray = new JSONArray(response);
                                    System.out.println("g2");
                                    for (int i = 0; i < jsonarray.length(); i++) {
                                        lb = new LibraryModel();
                                        System.out.println("g3");
                                        jsonobject = jsonarray.getJSONObject(i);
                                        String pdf = jsonobject.getString("pdf");
                                        String thumbnail = jsonobject.getString("thumbnail");
                                        lb.setPdf(pdf);
                                        lb.setThmb(thumbnail);

                                        // gm2.setGalimag(url2);
                                        liblist.add(lb);

                                    }

                                    LibraryAdapter adapter0 = new LibraryAdapter(liblist, getActivity());
                                    recyclerView.setAdapter(adapter0);
                                    progress.setVisibility(ProgressBar.GONE);


                                    recyclerView.addOnItemTouchListener(
                                            new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
                                                @Override
                                                public void onItemClick(View view, int position) {

                                                    Intent in = new Intent(getActivity(), PdfViewActivity.class);

                                                    String pdf = liblist.get(position).getPdf();
                                                    in.putExtra("pdf", pdf);
                                                    System.out.println("pdffff"+pdf);

//                                                    if(pdf.isEmpty())
//                                                    {
//                                                        android.support.v7.app.AlertDialog alertDialog = new android.support.v7.app.AlertDialog.Builder(getActivity()).create();
//                                alertDialog.setTitle("Alert");
//                                alertDialog.setMessage("Currently No Books are available ");
//                                alertDialog.setButton(android.support.v7.app.AlertDialog.BUTTON_NEUTRAL, "OK",
//                                        new DialogInterface.OnClickListener() {
//                                            public void onClick(DialogInterface dialog, int which) {
//                                                dialog.dismiss();
//                                                Intent i = new Intent(getActivity(), MainActivity.class);
//                                                startActivity(i);
////                                        but_regcrc1.setBackgroundResource(R.color.butnbakcolr);
////                                        but_regcrc1.setTextColor(getResources().getColor(R.color.White));
//
//                                            }
//                                        });
//                                alertDialog.show();
//
//                                                    }


                                                    startActivity(in);

                                                }
                                            })
                                    );


                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                        else
                        {
                            System.out.println("nothing to displayy");
                        }
                    }


                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //tv.setText("That didn't work!");

                    }
                });

        queue3.add(stringRequest3);





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
