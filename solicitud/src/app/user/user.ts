import { FormGroup } from "@angular/forms";

export interface User {
    id ?: number,
    name: string,
    email: string,
    password: string,
    position: string,
    active: boolean,
    roles: Role[],
    signatureUrl?: string
}

export interface UserResolved {
    user: User;
    error ?: any;
    form : FormGroup
}

export interface Role {
    roleName: string;
}