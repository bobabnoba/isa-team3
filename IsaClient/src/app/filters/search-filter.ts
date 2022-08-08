export class SearchFilter {
    constructor(
      public text: string = '',
      public sort: string = 'NO_SORT',
      public tags: string[] = [],
      public country: string = '',
      public startDate: Date = new Date(),
      public endDate: Date = new Date(),
      public people: number = 2,
      public type: string[] = [],
      public rating: number = 0
    ) {}
  }
  