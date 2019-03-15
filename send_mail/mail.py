import config
import csv
import sys
import smtplib
import datetime
from email.mime.multipart import MIMEMultipart
from email.mime.text import MIMEText
from email.mime.image import MIMEImage

def generate_mail(recipient_mail, timestamp, objects):
    msgRoot = MIMEMultipart()
    msgRoot['Subject'] = config.mail_subj
    msgRoot['From'] = config.username
    msgRoot['To'] = recipient_mail

    # img_data = open(picture, 'rb').read()

    html_noimage_text = '''
    <!DOCTYPE html>
    <html>
    <body>
    <div style="display: flex; justify-content: center;">
        <font face="Sans-serif">
            <h1>We were watching...</h1>
        </font>
    </div>

    <br>

    <div style="display: flex; justify-content: center;">
        <font face="Sans-serif">
                <h3>Following objects were detected</h3>
        </font>
    </div>

    <div  style="margin-left:20px;">
        <font face="Sans-serif">
                ''' + objects + '''
        </font>
    </div>

    <br>

    <div style="display: flex; justify-content: center;">
        <font face="Sans-serif">
                <h3>''' + "Date: " + timestamp[0] + "<br>Time: " + timestamp[1] + '''</h3>
        </font>
    </div>

    </body>
    </html>

    '''

    msgRoot.attach(MIMEText(html_noimage_text, 'html'))
    # msgRoot.attach(MIMEImage(img_data, name="frame-" + timestamp[0] + "-" + timestamp[1]))



    return msgRoot.as_string()

def smtp_main(timestamp, objects):
    smtp = smtplib.SMTP(config.mail_host, config.mail_port)
    smtp.starttls()
    print("Attempting to login...")
    smtp.login(config.username, config.password)

    print("Sending mails...")
    with open(config.csv_file) as csv_file:
        recipients = csv.reader(csv_file, delimiter=',')

        for recipient in recipients:
            try:
                mmail = generate_mail(recipient[config.email_index], timestamp, objects)

                smtp.sendmail(config.username, recipient[config.email_index], mmail)
                print('Mail sent to... ' + recipient[config.email_index])
            except Exception as e:
                print("Cannot send mail to " + recipient[config.email_index] + "\n" + str(e))

    smtp.quit()

if __name__=='__main__':
    objects = sys.argv[1]

    objects = objects.replace(",", "<br>")

    smtp_main([datetime.datetime.now().strftime("%A, %d %B, %Y"), datetime.datetime.now().strftime("%I:%M%p")], objects)