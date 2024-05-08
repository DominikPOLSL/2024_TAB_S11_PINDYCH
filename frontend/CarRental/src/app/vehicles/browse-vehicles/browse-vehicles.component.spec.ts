import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BrowseVehiclesComponent } from './browse-vehicles.component';

describe('BrowseVehiclesComponent', () => {
  let component: BrowseVehiclesComponent;
  let fixture: ComponentFixture<BrowseVehiclesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BrowseVehiclesComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(BrowseVehiclesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
