package email;

public class EmailList {
    String[] emails;
    int nextIndex = 0;
    int arrSize = 0;

    public EmailList(int size) {
        this.arrSize = size;
        this.emails = new String[size];
    }

    public void addEmail(String email) {
        this.emails[this.nextIndex] = email;
        this.nextIndex++;
        if (this.nextIndex >= this.arrSize) {
            this.nextIndex = 0;
        }
    }

    public void printEmails() {
        for(int i = 0; i<emails.length; i++) {
            if(null == this.emails[i]) {
                continue;
            }
            System.out.println(this.emails[i]);
        }
    }

    public boolean foundMatch(String input) {
        for(int i = 0; i < this.emails.length; i++) {
            if(null == this.emails[i]) {
                continue;
            }
            if(this.emails[i].equalsIgnoreCase(input)){
                return true;
            }
        }
        return false;
    }
}
