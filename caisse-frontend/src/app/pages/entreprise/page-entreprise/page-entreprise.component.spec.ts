import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PageEntrepriseComponent } from './page-entreprise.component';

describe('PageEntrepriseComponent', () => {
  let component: PageEntrepriseComponent;
  let fixture: ComponentFixture<PageEntrepriseComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [PageEntrepriseComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(PageEntrepriseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
