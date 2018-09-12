package meridian.com.etsdcapp.schedule;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import meridian.com.etsdcapp.R;
import meridian.com.etsdcapp.adapter.ScheduleWeekAdapter;
import meridian.com.etsdcapp.adapter.WeekListAdapter;
import meridian.com.etsdcapp.adapter.WeekListAdapter1;
import meridian.com.etsdcapp.model.ScheduleModel;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the

 * to handle interaction events.
 * Use the {@link WeekCourseListFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class WeekCourseListFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("MMMM");
    private static final   SimpleDateFormat weekdayNameFormat = new SimpleDateFormat("EEE", Locale.getDefault());
    TextView tv;
    ListView lv;
    private static final String DATE_PICKER_DATE_KEY = "date_picker_date_key";
    private static final String DATE_PICKER_POSITION_KEY = "date_picker_position_key";
ProgressDialog pd;
    private TextView tvDate;
    private TextView tvPosition,txt_schnam;
RecyclerView rv;
    TextView txttim;
LinearLayout layblnk;
    ArrayList<String> arrayList_sched_week_day=new ArrayList<>();
    RecyclerView recyclsch;
    ScheduleWeekAdapter rvsch;
    ArrayList<ScheduleModel> arrsch=new ArrayList<>();
    private int position;
    private long date;
    public static  String dateselsched;
    private String k,datesel;
    WeekListAdapter schwek;
    ProgressBar progress;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.

     * @return A new instance of fragment WeekCourseListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WeekCourseListFragment newInstance(int position, long date)
    {
        WeekCourseListFragment fragment = new WeekCourseListFragment();
        Bundle bundle = new Bundle();
       // ScheduleModel sch=new ScheduleModel();
        bundle.putInt(DATE_PICKER_POSITION_KEY, position);
        bundle.putLong(DATE_PICKER_DATE_KEY, date);
System.out.println("DATE_PICKER_DATE_KEY"+date);
        String datesel=  weekdayNameFormat.format(date).toUpperCase();

     dateselsched=datesel;
        System.out.println("dateselpg"+dateselsched);
        fragment.setArguments(bundle);
        return fragment;
    }
    public WeekCourseListFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        position = getArguments().getInt(DATE_PICKER_POSITION_KEY, -1);
        date = getArguments().getLong(DATE_PICKER_DATE_KEY, +1);
     // datesel=  weekdayNameFormat.format(date).toUpperCase();
        k=SIMPLE_DATE_FORMAT.format(date);




    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View v;
        v=inflater.inflate(R.layout.fragment_week_course_list1, container, false);

        progress = (ProgressBar)v.findViewById(R.id.progress_bar);




        return  v;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
      //  txt_schnam = (TextView) view.findViewById(R.id.txt_schcrcnam);
        lv=(ListView)  view.findViewById(R.id.listView);
       // recyclsch= (RecyclerView) view.findViewById(R.id.recysched);
       // LinearLayoutManager llm = new LinearLayoutManager(getContext());
     // recyclsch.setLayoutManager(llm);
      //  layblnk= (LinearLayout) view.findViewById(R.id.layblnk);
        progress.setVisibility(ProgressBar.VISIBLE);
//        pd = new ProgressDialog(getActivity());
//        pd.setTitle("Loading...");
//        pd.setMessage("Please wait...");
//        pd.setCancelable(true);
//        pd.show();

        // rv.setHasFixedSize(true);
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        String url = "http://www.app.etsdc.com/response.php?fid=16";


// Request a string response from the provided URL.

        StringRequest stringRequest1 = new StringRequest
                (Request.Method.GET, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        //  tv.setText("Response is: "+ response);

                        System.out.println("++++++++++++++RESPONSE+++++++++++++++ courses :" + response);

//
//                        JSONObject jsonObject = null;
                        try {
                            JSONArray jsonarray = new JSONArray(response);
                            System.out.println("00");
                            for (int i = 0; i < jsonarray.length(); i++) {
                              ScheduleModel sch=new ScheduleModel();
                                System.out.println("01");
                                JSONObject jsonobject = jsonarray.getJSONObject(i);

                                String schedule_id = jsonobject.getString("schedule_id");
                                String course = jsonobject.getString("course");
                                String day = jsonobject.getString("day");
                                String time = jsonobject.getString("time");
                               // String daynw=weekdayNameFormat.format(day);
                                sch.setTim(time);
                                sch.setCrcnam(course);
                                sch.setDay(day);
                                sch.setSchid(schedule_id);
                                arrsch.add(sch);
                                arrayList_sched_week_day.add(day);

                                    System.out.println("datesel"+datesel);
                                    System.out.println("datenwdddd"+day);
                               // pd.dismiss();
                                progress.setVisibility(ProgressBar.GONE);

                                }



                            if (getActivity() != null) {


                                schwek = new WeekListAdapter(arrsch,getContext());
                                lv.setAdapter(schwek);
                                System.out.println("scheddd");
                            }
                            System.out.println("1");

                         //  pd.dismiss();


                            System.out.println("2");





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

        queue.add(stringRequest1);




    }

    @Override
    public void onResume() {
        super.onResume();
    }

    // TODO: Rename method, update argument and hook method into UI event

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */

}
