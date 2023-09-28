package ru.vk.itmo.test.kovalchukvladislav;

import ru.vk.itmo.Dao;
import ru.vk.itmo.Entry;
import ru.vk.itmo.kovalchukvladislav.MemorySegmentDao;
import ru.vk.itmo.test.DaoFactory;

import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

@DaoFactory
public class MemorySegmentDaoFactory implements DaoFactory.Factory<MemorySegment, Entry<MemorySegment>> {
    private static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;
    private static final ValueLayout.OfByte DEFAULT_VALUE_LAYOUT = ValueLayout.JAVA_BYTE;

    @Override
    public Dao<MemorySegment, Entry<MemorySegment>> createDao() {
        return new MemorySegmentDao();
    }

    @Override
    public String toString(MemorySegment memorySegment) {
        if (memorySegment == null) {
            return null;
        }
        return new String(memorySegment.toArray(DEFAULT_VALUE_LAYOUT), DEFAULT_CHARSET);
    }

    @Override
    public MemorySegment fromString(String data) {
        if (data == null) {
            return null;
        }
        return MemorySegment.ofArray(data.getBytes(DEFAULT_CHARSET));
    }

    @Override
    public Entry<MemorySegment> fromBaseEntry(Entry<MemorySegment> baseEntry) {
        return baseEntry;
    }
}
