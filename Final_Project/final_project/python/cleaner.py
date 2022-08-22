import datetime

import pandas as pd


def _getToday():
    return datetime.date.today().strftime("%Y%m%d")

if __name__ == '__main__':
    data_path = "/home/phannt8/Documents/masterdev/Final_Project/final_project/data/netflix_data.csv"
    output_path = '/home/phannt8/Documents/masterdev/Final_Project/final_project/data/'

    df = pd.read_csv(data_path)

    df = df.dropna(how='any', subset=['cast', 'director']).copy()

    df['country'].fillna('Missing', inplace=True)
    df['date_added'].fillna('Missing', inplace=True)
    df['rating'].fillna('Missing', inplace=True)
    df['duration'].fillna('Missing', inplace=True)

    df["date_added"] = pd.to_datetime(df['date_added'])
    df['year_added'] = df['date_added'].dt.year
    df['month_added'] = df['date_added'].dt.month

    df['season_count'] = df.apply(lambda x: x['duration'].split(" ")[0] if "Season" in x['duration'] else "", axis=1)
    df['duration'] = df.apply(lambda x: x['duration'].split(" ")[0] if "Season" not in x['duration'] else "", axis=1)

    df = df.rename(columns={"listed_in": "genre"})
    df['genre'] = df['genre'].apply(lambda x: x.split(",")[0])
    df = df.drop('cast', axis=1)

    df = df.replace('', 0)

    df.to_csv(output_path + 'netflix_cleaned_{}.csv'.format(_getToday()), index=False, header=True)

    print(df.head(5))
