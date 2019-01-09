import { MessageEnum } from './messages/message.enum';

export class FormValidation {

    public editMode = false;
    public showMessage = false;
    public valid = false;
    public alreadyNew = true;
    public message = '';

    constructor() { }

    validate(message?: string) {
        this.valid = true;
        this.showMessage = true;
        this.editMode = true;
        this.message = message;
    }

    invalidate(message: string) {
        this.valid = false;
        this.showMessage = true;
        this.message = message;
    }

    reset() {
        this.editMode = false;
        this.showMessage = false;
        this.valid = false;
        this.alreadyNew = true;
        this.message = '';
    }

    getMessageEnum(): MessageEnum {
        if (!this.valid) {
            return MessageEnum.error;
        }
        return MessageEnum.success;
    }
}
