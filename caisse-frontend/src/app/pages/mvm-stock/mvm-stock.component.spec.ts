import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MvmStockComponent } from './mvm-stock.component';

describe('MvmStockComponent', () => {
  let component: MvmStockComponent;
  let fixture: ComponentFixture<MvmStockComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [MvmStockComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(MvmStockComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
