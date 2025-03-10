package com.dimetro.discordbot.musicservice.handler.searcher.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dimetro.discordbot.musicservice.handler.entity.*;
import com.dimetro.discordbot.musicservice.handler.searcher.core.data.*;
import com.dimetro.discordbot.musicservice.handler.searcher.core.exception.UnsupportedSearchDataConversionException;

public abstract class DataConverter {
    
    protected final Map<Class<? extends SearchData>, SearchDataConverter> converterMap = new HashMap<>();

    public DataConverter() {
        setupConverterMap();
    }

    protected abstract void setupConverterMap();

    public SongSearch convert(SearchData data) {
        
        SearchDataConverter converter = converterMap.get(data.getClass());
        
        if (converter != null) {
            return converter.convert(data);
        } else {
            throw new UnsupportedSearchDataConversionException(data.getClass());
        }
    }

    public List<SongSearch> convert(List<SearchData> data) {

        if (data == null) {
            return new ArrayList<>();
        }

        List<SongSearch> searches = new ArrayList<>();

        for (SearchData searchData : data) {
            SongSearch search = convert(searchData);
            if (search != null) {
                searches.add(search);
            }
        }

        return searches;
    }

}
