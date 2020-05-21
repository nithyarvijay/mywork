DROP TABLE IF EXISTS TBL_BOOK;
 
CREATE TABLE TBL_BOOK (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  book_name VARCHAR(250) NOT NULL,
  author_name VARCHAR(250) NOT NULL,
  publisher VARCHAR(250) DEFAULT NULL
);
 
INSERT INTO TBL_BOOK (book_name, author_name, publisher) VALUES
  ('Alice in Wonderland', 'Dangote', 'Wonder Publisher'),
  ('Harry Whats this', 'Gates', 'Global Publisher'),
  ('Folrunsho', 'Alakija', 'Magnus Publishers');

COMMIT;  

SELECT * FROM TBL_BOOK; 