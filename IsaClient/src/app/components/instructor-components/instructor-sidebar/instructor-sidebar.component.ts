import { Component, OnInit } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { DeleteAccoutRequest } from 'src/app/interfaces/delete-accout-request';
import { DeleteAccountService } from 'src/app/services/delete-account-service/delete-account.service';
import { StorageService } from 'src/app/services/storage-service/storage.service';
import { AddAdventureComponent } from '../../adventure-components/add-adventure/add-adventure.component';

@Component({
  selector: 'app-instructor-sidebar',
  templateUrl: './instructor-sidebar.component.html',
  styleUrls: ['./instructor-sidebar.component.css']
})
export class InstructorSidebarComponent implements OnInit {
  
  toggle : boolean = false;

  constructor(private _deleteAccountService : DeleteAccountService, private _storageService : StorageService,
    private _matDialog : MatDialog,  private _snackBar : MatSnackBar) { }

  ngOnInit(): void {
  }

  toggleMe(){
    this.toggle = !this.toggle;
  }

  openAddAdvModal(){

    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = false;
    dialogConfig.id = 'modal-component';
    dialogConfig.width = '1100px';
    const dialogRef = this._matDialog.open(AddAdventureComponent, dialogConfig);

    dialogRef.afterClosed().subscribe(res =>
      {
        this._snackBar.open('New adventure successfully added.', '',
        {duration : 3000, panelClass: ['snack-bar']}
      );
      })
  }

  deleteAccount(){
    let request = {
      email : this._storageService.getEmail(),
      explanation : "I want to delete my account bc this shit suuucks",
    } as DeleteAccoutRequest;
    this._deleteAccountService.createDeletionRequest(request).subscribe();
  }
  
}
