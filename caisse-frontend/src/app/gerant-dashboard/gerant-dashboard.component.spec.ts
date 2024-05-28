import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GerantDashboardComponent } from './gerant-dashboard.component';

describe('GerantDashboardComponent', () => {
  let component: GerantDashboardComponent;
  let fixture: ComponentFixture<GerantDashboardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [GerantDashboardComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(GerantDashboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
