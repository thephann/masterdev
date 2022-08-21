import csv
import datetime
import json

from kafka import KafkaProducer
import pandas as pd

def _getToday():
    return datetime.date.today().strftime("%Y%m%d")

class MessageProducer:
    broker = ""
    topic = ""
    producer = None

    def __init__(self, broker, topic):
        self.broker = broker
        self.topic = topic
        self.producer = KafkaProducer(bootstrap_servers=self.broker,
                                      value_serializer=lambda x: json.dumps(x).encode('utf-8'),
                                      acks='all',
                                      retries=3)

    def send_msg(self, df):
        print("sending message...")
        try:
            for row in df.iterrows():
                msg = row[1].to_dict()
                self.producer.send(self.topic, msg)
                self.producer.flush()
            print("message sent successfully...")
            return {'status_code': 200, 'error': None}
        except Exception as ex:
            return ex


if __name__ == '__main__':
    broker = 'server:9092'
    topic = 'netflix'

    data_path = '/home/phannt8/Documents/masterdev/Final_Project/final_project/data/'

    netflix_df = pd.read_csv(data_path + 'netflix_cleaned_{}.csv'.format(_getToday()))
    # print(netflix_df.head(5))
    message_producer = MessageProducer(broker, topic)

    resp = message_producer.send_msg(netflix_df)
