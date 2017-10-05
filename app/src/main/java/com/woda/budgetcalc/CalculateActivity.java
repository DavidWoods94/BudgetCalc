package com.woda.budgetcalc;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PieChart;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

public class CalculateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate);

    }

    //This way all of the information is not lost
    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = this.getIntent();
        Bundle b = intent.getExtras();
        String[] numbers = b.getStringArray(MainActivity.EXTRAS);
        Integer totalExpenses = 0;
        Integer monthlyExpenses = 0;

        //Calculating the total expenses
        for(int i = 1; i < numbers.length; i++){

            totalExpenses += Integer.parseInt(numbers[i]);
        }

        //Get the textview
        TextView salary = (TextView) findViewById(R.id.salary_result);
        //Set the text to the appropriate value
        salary.setText(Integer.toString(Integer.parseInt(numbers[0])/4 * 52));

        TextView monthly = (TextView) findViewById(R.id.monthlyIncomeResult);
        monthly.setText(numbers[0]);

        TextView savings = (TextView) findViewById(R.id.savingsResult);
        savings.setText(Integer.toString(Integer.parseInt(numbers[1])*12));

        TextView emergency = (TextView) findViewById(R.id.emergencyResult);
        emergency.setText(Integer.toString(Integer.parseInt(numbers[2])*12));

        TextView expenses = (TextView) findViewById(R.id.expensesResult);
        //Expenses don't include the first 3 values in the array.
        for(int i = 3; i<numbers.length; i++) {
            monthlyExpenses += Integer.parseInt(numbers[i]);
        }
        expenses.setText(Integer.toString(monthlyExpenses));

        TextView budgetR = (TextView) findViewById(R.id.budgetResult);
        Integer budget = Integer.parseInt(numbers[0]) - totalExpenses;
        budgetR.setText(Integer.toString(Integer.parseInt(numbers[0]) - totalExpenses));

        //if you saved money this year the text is green! Good job
        if(budget>0) {
            budgetR.setTextColor(Color.GREEN);
        }
        else if(budget<0) {
            budgetR.setTextColor(Color.RED);
        }
        makeChart(totalExpenses);
    }

    public void goBack(View view){
        finish();
    }
    //Makes the pie chart and puts it into the view
    private void makeChart(int totalExpenses){

        //These are all the results from the previous view
        String[] numbers = getIntent().getExtras().getStringArray(MainActivity.EXTRAS);

        //Section names
        String[] code = new String[] {
                "General Savings", "Emergency Fund", "Housing", "Utilities", "Cellphone", "Food", "Entertainment", "Health",
                "Car Insurance", "Car Payments", "Personal", "Gas", "Shopping"
        };

        //Values of the sections in percentages
        double[] distribution = { Double.parseDouble(numbers[1])/totalExpenses*100, Double.parseDouble(numbers[2])/totalExpenses*100,
                Double.parseDouble(numbers[3])/totalExpenses*100, Double.parseDouble(numbers[4])/totalExpenses*100,
                Double.parseDouble(numbers[5])/totalExpenses*100, Double.parseDouble(numbers[6])/totalExpenses*100,
                Double.parseDouble(numbers[7])/totalExpenses*100, Double.parseDouble(numbers[8])/totalExpenses*100,
                Double.parseDouble(numbers[9])/totalExpenses*100, Double.parseDouble(numbers[10])/totalExpenses*100,
                Double.parseDouble(numbers[11])/totalExpenses*100, Double.parseDouble(numbers[12])/totalExpenses*100,
                Double.parseDouble(numbers[13])/totalExpenses*100
        };

        //Colors of the sections
        int[] colors = { Color.BLUE, Color.MAGENTA, Color.GREEN, Color.CYAN, Color.RED,
                Color.YELLOW, Color.parseColor("#8A2BE2"), Color.parseColor("#FF8C00"), Color.parseColor("#FF1493"), Color.parseColor("#ADFF2F"),
                Color.parseColor("#87CEFA"), Color.parseColor("#00FA9A"), Color.parseColor("#8B4513")
        };

        //Making a CategorySeries for the pie chart
        CategorySeries distributionSeries = new CategorySeries("Expenses");
        for(int i=0 ;i < distribution.length;i++){
            //Adding a slice with its values and name to the chart
            distributionSeries.add(code[i], distribution[i]);
        }

        //Renderer for the  chart.
        DefaultRenderer defaultRenderer  = new DefaultRenderer();
        //Renderer for each section
        for(int i = 0 ;i<distribution.length;i++){
            SimpleSeriesRenderer seriesRenderer = new SimpleSeriesRenderer();
            seriesRenderer.setColor(colors[i]);
            seriesRenderer.setDisplayChartValues(true);
            // Adding a renderer for a slice
            defaultRenderer.addSeriesRenderer(seriesRenderer);
        }

        //Pie chart settings
        defaultRenderer.setChartTitleTextSize(50);
        defaultRenderer.setChartTitle("Monthly Expenses");
        defaultRenderer.setLabelsColor(Color.BLACK);
        defaultRenderer.setZoomButtonsVisible(false);
        defaultRenderer.setLegendTextSize(50);
        defaultRenderer.setLabelsTextSize(50);
        defaultRenderer.setPanEnabled(false);
        defaultRenderer.setZoomEnabled(false);
        defaultRenderer.setShowLegend(false);

        final PieChart pieChart = new PieChart(distributionSeries, defaultRenderer);
        //Put chart into view
        GraphicalView chartView = ChartFactory.getPieChartView(getApplicationContext(), distributionSeries,
                defaultRenderer);
        chartView.setId(R.id.pie);

        //Put that view into its container
        GridLayout gridLayout = (GridLayout) findViewById(R.id.chartContainer);
        gridLayout.addView(chartView);
    }


}
