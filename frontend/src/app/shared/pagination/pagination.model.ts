class Pagination {
    page: number;
    size: number;
    directon: string;
    orderBy: string;
}

interface Page {
    content: any;
    totalElements: number;
    totalPages: number;
    first: boolean;
    last: boolean;
    number: number;
    empty: boolean;
}

export { Pagination, Page };
