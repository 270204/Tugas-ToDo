package ap.mobile.to_do;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Activity> activities;
    private ActivityAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView rvToDo = findViewById(R.id.rvToDo);
        rvToDo.setLayoutManager(new LinearLayoutManager(this));
        this.activities = new ArrayList<>();
        this.adapter = new ActivityAdapter((Context) this, (ArrayList<Activity>) activities);

        new FetchDataTask().execute();
    }

    private class FetchDataTask extends AsyncTask<Void, Void, List<Activity>> {

        @Override
        protected List<Activity> doInBackground(Void... voids) {
            List<Activity> activityList = new ArrayList<>();
            try {
                URL url = new URL("https://mgm.ub.ac.id/todo.php");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }

                reader.close();

                JSONArray jsonArray = new JSONArray(response.toString());

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    String id = jsonObject.getString("id");
                    String what = jsonObject.getString("what");
                    String time = jsonObject.getString("time");

                    Activity newActivity = new Activity(id, what, time);
                    activityList.add(newActivity);
                }

            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }

            return activityList;
        }

        @Override
        protected void onPostExecute(List<Activity> activityList) {
            super.onPostExecute(activityList);
            activities.clear();
            activities.addAll(activityList);
        }
    }
}
