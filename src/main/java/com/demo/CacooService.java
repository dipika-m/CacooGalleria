package com.demo;

import java.io.InputStream;
import java.util.List;

/**
 * @author Dipika
 */
public interface CacooService {

  /**
   * Get the list of cacoo diagram ids for the current user.
   *
   * @return The list of diagram ids for the current user.
   */
  List<String> getCacooDiagramIds() throws CacooException;

  /**
   * Loads the Cacoo Diagram  for the current user.
   *
   * @param id the id or the photo.
   * @return The Cacoo Diagram.
   */
  InputStream loadCacooDiagram(String id) throws CacooException;
}
