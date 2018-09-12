package meridian.com.etsdcapp.library;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
import meridian.com.etsdcapp.adapter.VideoAdapter;
import meridian.com.etsdcapp.course.MainCoursesActivity;
import meridian.com.etsdcapp.model.LibraryModel;
import meridian.com.etsdcapp.model.VideoModel;
import meridian.com.etsdcapp.schedule.CalendarRegisteredCoursesActivity1;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link VideoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VideoFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    int color;
    int sel;
    int id;
    RecyclerView rv;
   ArrayList<LibraryModel> liblist;
    ArrayList<VideoModel> vidarr;

    private OnFragmentInteractionListener mListener;

    public VideoFragment() {
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
    public static VideoFragment newInstance(String param1, String param2) {
        VideoFragment fragment = new VideoFragment();
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



        View view = inflater.inflate(R.layout.fragment_video, container, false);
        rv = (RecyclerView) view.findViewById(R.id.dummyfrag_scrollableviewlib1);

        GridLayoutManager llm = new GridLayoutManager(getContext(), 2);
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);
//           pd = new ProgressDialog(LibraryActivity.this);
//            pd.setTitle("Loading...");
//         pd.setMessage("Please wait...");
//            pd.setCancelable(true);
//            pd.show();

        // rv.setHasFixedSize(true);
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        String url = "http://www.app.etsdc.com/response.php?fid=12";


// Request a string response from the provided URL.

        StringRequest stringRequest1 = new StringRequest
                (Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        //  tv.setText("Response is: "+ response);

                        System.out.println("++++++++++++++RESPONSE+++++++++++++++ video:" + response);
//thumb-113107000000.jpg
                        if (response != null) {

                                if (response.equals("\"failed\"")) {


//                                    android.support.v7.app.AlertDialog alertDialog = new android.support.v7.app.AlertDialog.Builder(getActivity()).create();
//                                    alertDialog.setTitle("Alert");
//                                    alertDialog.setMessage("Currently No Books are available");
//                                    alertDialog.setButton(android.support.v7.app.AlertDialog.BUTTON_NEUTRAL, "OK",
//                                            new DialogInterface.OnClickListener() {
//                                                public void onClick(DialogInterface dialog, int which) {
//                                                    dialog.dismiss();
//                                                    Intent i = new Intent(getActivity(), MainActivity.class);
//                                                    startActivity(i);
////                                        but_regcrc1.setBackgroundResource(R.color.butnbakcolr);
////                                        but_regcrc1.setTextColor(getResources().getColor(R.color.White));
//
//                                                }
//                                            });
//                                    alertDialog.show();

                                } else {
                                    JSONArray jsonarray;
                                    JSONObject jsonobject;
                                    VideoModel vm;
                                    try {

                                        vidarr = new ArrayList<>();
                                        jsonarray = new JSONArray(response);

                                        System.out.println("g2");
                                        for (int i = 0; i < jsonarray.length(); i++) {
                                            vm = new VideoModel();
                                            jsonobject = jsonarray.getJSONObject(i);
                                            String vid_id = jsonobject.getString("vid_id");
                                            String video = jsonobject.getString("video");
                                            String vid_thumbnail = jsonobject.getString("vid_thumbnail");
                                            String title = jsonobject.getString("title");
                                            String url2 = "http://www.app.etsdc.com/uploads/video/" + vid_thumbnail;
                                            vm.setId(vid_id);
                                            vm.setThumbnail(url2);
                                            vm.setVideo(video);
                                            vm.setTitle(title);
                                            System.out.println("02");
                                            vidarr.add(vm);


                                        }


                                        VideoAdapter adapter0 = new VideoAdapter(vidarr, getActivity());
                                        System.out.println("1");
                                        rv.scheduleLayoutAnimation();
                                        rv.setAdapter(adapter0);
                                        rv.setHasFixedSize(true);
//                                pd.dismiss();


                                        rv.addOnItemTouchListener(
                                                new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
                                                    @Override
                                                    public void onItemClick(View view, int position) {
                                                        view.setBackgroundResource(R.drawable.opito);
                                                        Intent in = new Intent(getActivity(), VideoActivity.class);
                                                        String id = vidarr.get(position).getVideo();
                                                        System.out.println("ids" + id);
                                                        //  String nam=   arrcrs.get(position).getCoursenam();
                                                        // String img_url=   arrcrs.get(position).getThumbnail();
                                                        in.putExtra("id", id);
//                                                        if(id.isEmpty())
//                                                        {
//                                          android.support.v7.app.AlertDialog alertDialog = new android.support.v7.app.AlertDialog.Builder(getActivity()).create();
//                                            alertDialog.setTitle("Alert");
//                                            alertDialog.setMessage("Currently No Books are available");
//                                            alertDialog.setButton(android.support.v7.app.AlertDialog.BUTTON_NEUTRAL, "OK",
//                                                    new DialogInterface.OnClickListener() {
//                                                        public void onClick(DialogInterface dialog, int which) {
//                                                            dialog.dismiss();
//                                                            Intent i = new Intent(getActivity(), MainActivity.class);
//                                                            startActivity(i);
////                                        but_regcrc1.setBackgroundResource(R.color.butnbakcolr);
////                                        but_regcrc1.setTextColor(getResources().getColor(R.color.White));
//
//                                                }
//                                            });
//                                    alertDialog.show();
//                                                        }
                                                        // in.putExtra("nam", nam);
                                                        // in.putExtra("thmb", img_url);
                                                        startActivity(in);
                                                    }
                                                })
                                        );


                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                        }

                    }


                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //tv.setText("That didn't work!");

                    }
                });

        queue.add(stringRequest1);





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
