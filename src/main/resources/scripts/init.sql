
CREATE DATABASE realestatedb;

CREATE TABLE public.property_entity (
	id varchar(255) NOT NULL,
	bathrooms int4 NOT NULL,
	bedrooms int4 NOT NULL,
	description varchar(255) NULL,
	price numeric(38, 2) NULL,
	square_footage int4 NOT NULL,
	status varchar(255) NULL,
	city_id varchar(255) NULL,
	seller_id varchar(255) NULL,
	title varchar(255) NULL,
	CONSTRAINT property_entity_pkey PRIMARY KEY (id),
);

ALTER TABLE public.property_entity ADD CONSTRAINT fk_seller_id FOREIGN KEY (seller_id) REFERENCES public.user_info(id);
ALTER TABLE public.property_entity ADD CONSTRAINT fk_city_id FOREIGN KEY (city_id) REFERENCES public.city(id);

