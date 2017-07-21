/*
 * Copyright (c)  2017 Alen Turković <alturkovic@gmail.com>
 *
 * Permission to use, copy, modify, and distribute this software for any
 * purpose with or without fee is hereby granted, provided that the above
 * copyright notice and this permission notice appear in all copies.
 *
 * THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES
 * WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR
 * ANY SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES
 * WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN
 * ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF
 * OR IN CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.
 */

package com.github.alturkovic.asn.ber.converter;

import com.github.alturkovic.asn.ber.util.HexUtils;
import com.github.alturkovic.asn.converter.AsnConverter;
import com.github.alturkovic.asn.exception.AsnConvertException;

// 0 = false
// -1 = true (255 | 0xFF)

public class BooleanConverter implements AsnConverter<byte[], Boolean> {

    @Override
    public Boolean decode(final byte[] data) {
        if (data == null) {
            return null;
        }

        if (data.length != 1) {
            throw new AsnConvertException("Data has multiple bytes: " + HexUtils.encode(data));
        }

        if (data[0] == 0) {
            return false;
        }

        if (data[0] == -1) {
            return true;
        }

        throw new AsnConvertException(String.format("%s doesn't represent boolean", HexUtils.encode(data)));
    }

    @Override
    public byte[] encode(final Boolean data) {
        if (data == null) {
            return null;
        }

        return new byte[]{(byte) (data ? -1 : 0)};
    }
}