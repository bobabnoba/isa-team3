import { Component, OnInit } from '@angular/core';
import { DeleteAccoutRequest } from 'src/app/interfaces/delete-accout-request';
import { DeleteAccountService } from 'src/app/services/delete-account-service/delete-account.service';
import { StorageService } from 'src/app/services/storage-service/storage.service';

@Component({
  selector: 'app-instructor-sidebar',
  templateUrl: './instructor-sidebar.component.html',
  styleUrls: ['./instructor-sidebar.component.css']
})
export class InstructorSidebarComponent implements OnInit {
  
  toggle : boolean = false;

  constructor(private _deleteAccountService : DeleteAccountService, private _storageService : StorageService) { }

  ngOnInit(): void {
  }

  toggleMe(){
    this.toggle = !this.toggle;
  }

  deleteAccount(){
    let request = {
      email : this._storageService.getEmail(),
      explanation : "I want to delete my account bc this shit suuucks",
    } as DeleteAccoutRequest;
    this._deleteAccountService.createDeletionRequest(request).subscribe();
  }
  
}
