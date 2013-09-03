package ch.ethz.vppserver.ippclient;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import ch.ethz.vppserver.schema.ippclient.AttributeGroup;
import ch.ethz.vppserver.schema.ippclient.AttributeList;
import ch.ethz.vppserver.schema.ippclient.Tag;
import ch.ethz.vppserver.schema.ippclient.TagList;

/**
 * Copyright (C) 2008 ITS of ETH Zurich, Switzerland, Sarah Windler Burri
 * 
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 3 of the License, or (at your option) any
 * later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE.
 * 
 * See the GNU Lesser General Public License for more details. You should have
 * received a copy of the GNU Lesser General Public License along with this
 * program; if not, see <http://www.gnu.org/licenses/>.
 */
public class IppJaxb {

  private final static String TAG_LIST_FILENAME = "ipp-list-of-tag.xml";
  private final static String ATTRIBUTE_LIST_FILENAME = "ipp-list-of-attributes.xml";

  private List<Tag> _tagList = null;
  private List<AttributeGroup> _attributeGroupList = null;

  IppJaxb() throws FileNotFoundException {
    InputStream tagListStream = IppJaxb.class.getResourceAsStream(TAG_LIST_FILENAME);
    InputStream attListStream = IppJaxb.class.getResourceAsStream(ATTRIBUTE_LIST_FILENAME);

    try {
		Serializer serializer = new Persister();
		TagList tagList=serializer.read(TagList.class, tagListStream);
		_tagList = tagList.getTag();

		serializer = new Persister();
		AttributeList attributeList = serializer.read(AttributeList.class, attListStream);
		_attributeGroupList = attributeList.getAttributeGroup();
	} catch (Exception e) {
		e.printStackTrace();
		throw new FileNotFoundException();
	}
  }

  /**
   * 
   * @return
   */
  public List<Tag> getTagList() {

    return _tagList;
  }

  /**
   * 
   * @return
   */
  public List<AttributeGroup> getAttributeGroupList() {
    return _attributeGroupList;
  }
}
