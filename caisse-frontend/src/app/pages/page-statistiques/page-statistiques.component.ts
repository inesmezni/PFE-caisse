import { Component, OnInit } from '@angular/core';
import {  registerables } from 'chart.js';
Chart.register(...registerables)
import { Chart } from 'chart.js';

@Component({
  selector: 'app-page-statistiques',
  templateUrl: './page-statistiques.component.html',
  styleUrl: './page-statistiques.component.css'
})
export class PageStatistiquesComponent implements OnInit{



  constructor() {}

  ngOnInit(): void {
    this.renderChart('bar', 'myChart');
    this.renderChart('doughnut', 'doughnutChart');
  }

  renderChart(type: any, id: any) {
    const ctx = document.getElementById(id) as HTMLCanvasElement;

    if (type === 'bar') {
      new Chart(ctx, {
        type: 'bar',
        data: {
          labels: ['2019', '2020', '2021', '2022', '2023', '2024'],
          datasets: [{
            label: '# Ventes annuelles',
            data: [5000, 6500, 4000, 7000, 17000, 21000],
            borderWidth: 1
          }]
        },
        options: {
          scales: {
            y: {
              beginAtZero: true
            }
          }
        }
      });
    } else if (type === 'doughnut') {
      new Chart(ctx, {
        type: 'doughnut',
        data: {
          labels: ['Eau', 'fraise', 'bananne'],
          datasets: [{
            label: 'My First Dataset',
            data: [300, 50, 100],
            backgroundColor: [
              'rgb(255, 99, 132)',
              'rgb(54, 162, 235)',
              'rgb(255, 205, 86)'
            ],
            hoverOffset: 4
          }]
        },
        options: {
          responsive: true,
          maintainAspectRatio: false
        }
      });
    }
  }
}