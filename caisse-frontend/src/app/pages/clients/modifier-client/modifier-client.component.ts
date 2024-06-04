import { Component, OnInit } from '@angular/core';
import { ClientService } from '../../../services/client/client.service';
import { client } from '../client'; 
import { Router, ActivatedRoute } from '@angular/router'; 
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-modifier-client',
  templateUrl: './modifier-client.component.html',
  styleUrls: ['./modifier-client.component.css'] 
})
export class ModifierClientComponent implements OnInit {

  ClientId!: number; 
  editClientForm!: FormGroup; 

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private clientService: ClientService,
    private fb: FormBuilder,
  ) {
    this.editClientForm = this.fb.group({
      nom: ['', Validators.required],
      prenom: ['', Validators.required],
      adresse: ['', Validators.required],
      mail: ['', Validators.required],
      numTel: ['', Validators.required],
    });
  }

  ngOnInit(): void {
    this.ClientId = +this.route.snapshot.paramMap.get('ClientId')!;
    this.clientService.getClientById(this.ClientId).subscribe(data => {
      this.editClientForm.patchValue(data);
    });
  }

  modifierClient() {
    this.clientService.modifierClient(this.ClientId, this.editClientForm.value)
      .subscribe({
        next: response => {
          console.log('Client updated successfully', response);
        },
        error: error => {
          console.error('Error updating client', error);
        }
      });
      this.router.navigate(['caissier-dashboard/client']);
  }

  cancel(): void {
    this.router.navigate(['caissier-dashboard/client']);
  }
}
