import { Component } from '@angular/core';
import { EmailService } from '../../services/email/email.service';

@Component({
  selector: 'app-promotion',
  templateUrl: './promotion.component.html',
  styleUrl: './promotion.component.css'
})
export class PromotionComponent {

  subject?: string;
  message?: string;

  constructor(private emailService: EmailService) {}

  sendEmail() {
    const email = {
      subject: this.subject,
      message: this.message
    };
    this.emailService.sendEmail(email).subscribe(
      () => {
        alert('Email envoyé avec succès!');
        this.subject = '';
        this.message = '';
      },
      error => {
        console.error('Erreur lors de l\'envoi de l\'email:', error);
        alert('Erreur lors de l\'envoi de l\'email. Veuillez réessayer plus tard.');
      }
    );
  }
}