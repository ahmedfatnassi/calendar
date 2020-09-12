import {AfterViewInit, Component, OnInit} from '@angular/core';
import {NbColorHelper, NbThemeService} from '@nebular/theme';
import {DatePipe} from '@angular/common';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  data: any;
  options: any;
  data1: any;
  options1: any;
  data2: any;
  options2: any;
   data3: any;
  options3: any;
   data4: any;
  options4: any;
  themeSubscription0: any;
  themeSubscription1: any;
  themeSubscription2: any;
  themeSubscription3: any;
  themeSubscription4: any;
  constructor(private theme: NbThemeService) {


    // bar
    this.themeSubscription0 = this.theme.getJsTheme().subscribe(config => {

      const colors: any = config.variables;
      const chartjs: any = config.variables.chartjs;

      this.data = {
        labels:[ 'February', 'March', 'April', 'May', 'June', 'July', 'August'],
        datasets: [{
          data: [65, 59, 80, 81, 56, 55, 40],
          label: 'Received Requests ',
          backgroundColor: NbColorHelper.hexToRgbA(colors.primaryLight, 0.8),
        }, {
          data: [28, 48, 40, 19, 86, 27, 90],
          label: 'Treated Requests',
          backgroundColor: NbColorHelper.hexToRgbA(colors.infoLight, 0.8),
        }],
      };

      this.options = {
        maintainAspectRatio: false,
        responsive: true,
        legend: {
          labels: {
            fontColor: chartjs.textColor,
          },
        },
        scales: {
          xAxes: [
            {
              gridLines: {
                display: false,
                color: chartjs.axisLineColor,
              },
              ticks: {
                fontColor: chartjs.textColor,
              },
            },
          ],
          yAxes: [
            {
              gridLines: {
                display: true,
                color: chartjs.axisLineColor,
              },
              ticks: {
                fontColor: chartjs.textColor,
              },
            },
          ],
        },
      };
    });


    // pie
    this.themeSubscription1 = this.theme.getJsTheme().subscribe(config => {

      const colors: any = config.variables;
      const chartjs: any = config.variables.chartjs;

      this.data1 = {
        labels: ['Approved Act', 'Disapproved Act', 'Doubted Act'],
        datasets: [{
          data: [600, 500, 100],
          backgroundColor: [colors.primaryLight, colors.danger, colors.successLight],
        }],
      };

      this.options1 = {
        maintainAspectRatio: false,
        responsive: true,
        scales: {
          xAxes: [
            {
              display: false,
            },
          ],
          yAxes: [
            {
              display: false,
            },
          ],
        },
        legend: {
          labels: {
            fontColor: chartjs.textColor,
          },
        },
      };
    });

    // line
    this.themeSubscription2 = this.theme.getJsTheme().subscribe(config => {

      const colors: any = config.variables;
      const chartjs: any = config.variables.chartjs;

      this.data2 = {
        labels: [ 'February', 'March', 'April', 'May', 'June', 'July', 'August'],
        datasets: [{
          data: [65, 59, 80, 81, 56, 55, 40],
          label: 'Average of Treated Act per Employee ',
          backgroundColor: NbColorHelper.hexToRgbA(colors.primary, 0.3),
          borderColor: colors.primary,
        }, /* {
          data: [28, 48, 40, 19, 86, 27, 90],
          label: 'Series B',
          backgroundColor: NbColorHelper.hexToRgbA(colors.danger, 0.3),
          borderColor: colors.danger,
        },*/
        ],
      };

      this.options2 = {
        responsive: true,
        maintainAspectRatio: false,
        scales: {
          xAxes: [
            {
              gridLines: {
                display: true,
                color: chartjs.axisLineColor,
              },
              ticks: {
                fontColor: chartjs.textColor,
              },
            },
          ],
          yAxes: [
            {
              gridLines: {
                display: true,
                color: chartjs.axisLineColor,
              },
              ticks: {
                fontColor: chartjs.textColor,
              },
            },
          ],
        },
        legend: {
          labels: {
            fontColor: chartjs.textColor,
          },
        },
      };
    });



    this.themeSubscription3 = this.theme.getJsTheme().subscribe(config => {
      const dte = new Date();
      dte.setDate(dte.getDate() - 1);
      console.log(dte.getDay());
      let daysdate = []
      for (let i = 6; i >= 0; i--) {
        daysdate[i] = dte.toDateString() ;
        dte.setDate(dte.getDate() - 1);
      }
      console.log(daysdate)

      const colors: any = config.variables;
      const chartjs: any = config.variables.chartjs;

      this.data3 = {
        labels: daysdate,
        datasets: [{
          data: [1, 2, 4, 3, 7, 5, 6],
          label: 'Treated Acts ',
          backgroundColor: NbColorHelper.hexToRgbA(colors.primaryLight, 0.8),
        }],
      };

      this.options3 = {
        maintainAspectRatio: false,
        responsive: true,
        legend: {
          labels: {
            fontColor: chartjs.textColor,
          },
        },
        scales: {
          xAxes: [
            {
              gridLines: {
                display: false,
                color: chartjs.axisLineColor,
              },
              ticks: {
                fontColor: chartjs.textColor,
              },
            },
          ],
          yAxes: [
            {
              gridLines: {
                display: true,
                color: chartjs.axisLineColor,
              },
              ticks: {
                fontColor: chartjs.textColor,
              },
            },
          ],
        },
      };
    });
    // line
    this.themeSubscription4  = this.theme.getJsTheme().subscribe(config => {

      const colors: any = config.variables;
      const chartjs: any = config.variables.chartjs;

      this.data4 = {
        labels: [ 'February', 'March', 'April', 'May', 'June', 'July', 'August'],
        datasets: [ {
          data: [28, 48, 40, 19, 86, 27, 90],
          label: 'Average of Requests Treated time',
          backgroundColor: NbColorHelper.hexToRgbA(colors.danger, 0.3),
          borderColor: colors.danger,
        },
        ],
      };

      this.options4 = {
        responsive: true,
        maintainAspectRatio: false,
        scales: {
          xAxes: [
            {
              gridLines: {
                display: true,
                color: chartjs.axisLineColor,
              },
              ticks: {
                fontColor: chartjs.textColor,
              },
            },
          ],
          yAxes: [
            {
              gridLines: {
                display: true,
                color: chartjs.axisLineColor,
              },
              ticks: {
                fontColor: chartjs.textColor,
              },
            },
          ],
        },
        legend: {
          labels: {
            fontColor: chartjs.textColor,
          },
        },
      };
    });


  }



  ngOnInit() {}
  getListofLastMonths(index1:any , index2:any){
    const pipe = new DatePipe('en-US');
    // use moment

    const now = Date.now();
    const myFormattedDate = pipe.transform(now , 'M');
    const  y: number = +myFormattedDate
    console.log(y +1)
  }
}
