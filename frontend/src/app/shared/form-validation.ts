export class FormValidation {

    public editMode = false;
    public showMessage = false;
    public valid = false;
    public alreadyNew = true;
    public message = '';
    public typeMessage = 'info';

    constructor() { }

    validate(message: string) {
        this.valid = true;
        this.showMessage = true;
        this.editMode = true;
        this.message = message;
        if (!this.message || this.message === '') {
            this.message = 'Sucesso!';
        }
        this.typeMessage = 'success';
    }

    invalidate(message: string, errors?: [], fieldName = true) {
        this.valid = false;
        this.showMessage = true;
        this.message = message;
        if (errors && errors.length > 0) {
            this.message = `${this.message}: `;
            for (let i = 0; i < errors.length; i++) {
                if (!fieldName) {
                    this.message += `${errors[i]['messageString']}, `;
                } else {
                    this.message += `${errors[i]['fieldName']}, `;
                }
            }
            this.message = this.message.substr(0, this.message.length - 2);
        }
        if (!this.message || this.message === '') {
            this.message = 'Erro inesperado!';
        }
        this.typeMessage = 'danger';
    }

    reset() {
        this.editMode = false;
        this.showMessage = false;
        this.valid = false;
        this.alreadyNew = true;
        this.message = '';
        this.typeMessage = 'info';
    }
}
