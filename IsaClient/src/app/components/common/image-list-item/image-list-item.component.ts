import {
  Component,
  EventEmitter,
  Input,
  Output,
} from '@angular/core';
import { ImageListItem } from 'src/app/interfaces/image-list-item';

@Component({
  selector: 'app-image-list-item',
  templateUrl: './image-list-item.component.html',
  styleUrls: ['./image-list-item.component.css']
})
export class ImageListItemComponent {
  @Input()
  image?: ImageListItem;

  @Output()
  click = new EventEmitter<void>();

  onImageListItemClick(imageListItem: ImageListItem) {
    this.click.emit();
  }

}
