

CREATE TABLE track_ratings_by_user (
user UUID,
activity TIMEUUID,
rating INT,
album_title VARCHAR,
album_year INT,
track_title VARCHAR,
PRIMARY KEY (user, activity)
) WITH CLUSTERING ORDER BY (activity DESC);
