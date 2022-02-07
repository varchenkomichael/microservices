DROP TABLE IF EXISTS product;

CREATE TABLE product
(
    uniq_id                 VARCHAR PRIMARY KEY,
    sku                     VARCHAR(250),
    name_title              VARCHAR(250),
    description             TEXT,
    list_price              VARCHAR(250),
    sale_price              VARCHAR(250),
    category                VARCHAR(250),
    category_tree           VARCHAR(250),
    average_category_rating VARCHAR(250),
    product_url             TEXT,
    product_image_urls      TEXT,
    brand                   VARCHAR(250),
    total_number_reviews    VARCHAR(250),
    reviews                 TEXT
);

COPY product (uniq_id, sku, name_title, description, list_price, sale_price,
              category, category_tree, average_category_rating, product_url,
              product_image_urls, brand, total_number_reviews, reviews)
    FROM '/Users/mvarchenko/Downloads/jcpenney_com-ecommerce_sample.csv'
    CSV HEADER