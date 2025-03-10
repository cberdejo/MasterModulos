from datetime import datetime,timedelta
import random as rdm

def generate_random_dates(initial_date = datetime(2024,1,1), final_date=datetime(2025,1,2)):
        delta = final_date - initial_date
        random_days = rdm.randint(0,delta.days)
        return initial_date + timedelta(days=random_days)